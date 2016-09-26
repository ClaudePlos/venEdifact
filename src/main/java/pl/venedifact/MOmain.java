/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.venedifact;

/**
 *
 * @author k.skowronski
 */
import org.milyn.*;
import org.milyn.payload.JavaResult;
import org.milyn.container.*;
import org.milyn.event.report.*;
import org.milyn.io.*;
import org.xml.sax.*;

import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.venedifact.model.DokumentVO;
import pl.venedifact.model.PozycjeVO;
import pl.venedifact.model.wspolne.NrDok;
import pl.venedifact.model.wspolne.Pozycja;
import pl.venedifact.model.wspolne.TypDok;


public class MOmain {
    
    public static String sciezka = "c:\\temp\\edi\\EDI2.exp";

    private static byte[] messageIn = readInputMessage();

    private final Smooks smooks;
    
    
    private static volatile MOmain  instance = null;
    
    public static MOmain getInstance() throws IOException
    {
       if (instance == null) {
           try {
               instance = new MOmain();
           } catch (SAXException ex) {
               Logger.getLogger(MOmain.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return instance;  
    }
    

    protected MOmain() throws IOException, SAXException {
        // Instantiate Smooks with the config...
        smooks = new Smooks("smooks-config-MO.xml");
    }

    protected JavaResult runSmooksTransform(ExecutionContext executionContext) throws IOException, SAXException, SmooksException {
    	try {
            Locale defaultLocale = Locale.getDefault();
            Locale.setDefault(new Locale("en", "IE"));

            org.milyn.payload.JavaResult javaResult = new org.milyn.payload.JavaResult();

            // Configure the execution context to generate a report...
            executionContext.setEventListener(new HtmlReportGenerator("target/report/report.html"));

            // Filter the input message to the outputWriter, using the execution context...
            smooks.filterSource(executionContext, new StreamSource(new ByteArrayInputStream(messageIn)), javaResult);

            Locale.setDefault(defaultLocale);

            return javaResult;
        } finally {
            smooks.close();
        }
    }

    public static void main(String[] args) throws IOException, SAXException, SmooksException {
        System.out.println("\n\n==============Message In==============");
        System.out.println(new String(messageIn));
        System.out.println("======================================\n");

        pause("The EDI input stream can be seen above.  Press 'enter' to see how this stream is transformed the Order Object graph...");

        MOmain smooksMain = new MOmain();
        ExecutionContext executionContext = smooksMain.smooks.createExecutionContext();
        org.milyn.payload.JavaResult result = smooksMain.runSmooksTransform(executionContext);


        System.out.println("\n==============EDI as Java Object Graph=============");
        System.out.println(result.getBean("pz"));
        System.out.println(result.getBean("pozItemList"));
        System.out.println("======================================\n\n");
        
        DokumentVO dokumentPZ = new DokumentVO();
        dokumentPZ.setTyp( ((TypDok) result.getBean("typDok")).getTyp());
        dokumentPZ.setNumer( ((NrDok) result.getBean("nrDok")).getNrDokumentu() );
        
        List<PozycjeVO> pozycje  = new ArrayList<PozycjeVO>();
        
        List<Pozycja> pozPZ = (List<Pozycja>) result.getBean("pozItemList");
        
        for ( Pozycja poz :  pozPZ )
        {
            String pole = poz.getPole().toString();
            String delims = "[{}]+";
            String[] matches = pole.split(delims);
            System.out.println(result.getBean(matches.toString()));
        }
        

        pause("And that's it!  Press 'enter' to finish...");
    }

    private static byte[] readInputMessage() {
        try {
            return StreamUtils.readStream(new FileInputStream(sciezka));
        } catch (IOException e) {
            e.printStackTrace();
            return "<no-message/>".getBytes();
        }
    }

    private static void pause(String message) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("> " + message);
            in.readLine();
        } catch (IOException e) {
        }
        System.out.println("\n");
    }

    public org.milyn.payload.JavaResult runSmooksTransform() throws IOException, SAXException {
        ExecutionContext executionContext = smooks.createExecutionContext();
        return runSmooksTransform(executionContext);
    }
}
