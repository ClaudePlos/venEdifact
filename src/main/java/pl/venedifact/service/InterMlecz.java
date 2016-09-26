/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.venedifact.service;

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


public class InterMlecz {
    
    public String sciezka = "";

    private byte[] messageIn = readInputMessage();

    private Smooks smooks = null;
    
    private static volatile InterMlecz  instance = null;
    
    public static InterMlecz getInstance(  )
    { 
       if (instance == null) {
               instance = new InterMlecz();  
        }
        return instance;  
    }
    
    public void uzpelnij( String sciezkaPobrana ) 
    {
        sciezka = sciezkaPobrana;
        messageIn = readInputMessage();
    }
 
/*
    public InterMlecz() {

        try { 
            
        } catch (IOException ex) {
            Logger.getLogger(InterMlecz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(InterMlecz.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }
*/
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

    public void run(String[] args) throws IOException, SAXException, SmooksException {
        
        smooks = new Smooks("smooks-config-MO.xml");
        
        System.out.println("\n\n==============Message In==============");
        System.out.println(new String(messageIn));
        System.out.println("======================================\n");

        //pause("The EDI input stream can be seen above.  Press 'enter' to see how this stream is transformed the Order Object graph...");

        InterMlecz smooksMain = instance;
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
        

        //pause("And that's it!  Press 'enter' to finish...");
    }

    public byte[] readInputMessage() {
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
