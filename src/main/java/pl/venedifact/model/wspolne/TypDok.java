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
public class TypDok {
    
    private String typ;

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
    
    
    
    public String toString() {
        StringBuffer desc = new StringBuffer();

        desc.append("\ttypDok: " + typ + "\n");
        
        return desc.toString();
    }
    
}
