/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.venedifact.model.wspolne;

/**
 *
 * @author k.skowronski
 */
public class NrDok {
    
    private String nrDokumentu;

    public String getNrDokumentu() {
        return nrDokumentu;
    }

    public void setNrDokumentu(String nrDokumentu) {
        this.nrDokumentu = nrDokumentu;
    }
    
    
    
    public String toString() {
        StringBuffer desc = new StringBuffer();

        desc.append("\tnrDok: " + nrDokumentu + "\n");
        
        return desc.toString();
    }
    
}
