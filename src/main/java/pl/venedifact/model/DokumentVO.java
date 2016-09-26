/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.venedifact.model;

import java.util.List;

/**
 *
 * @author k.skowronski
 */
public class DokumentVO {
    
    private String typ;
    private String numer;
    private List<PozycjeVO> pozycje;

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public List<PozycjeVO> getPozycje() {
        return pozycje;
    }

    public void setPozycje(List<PozycjeVO> pozycje) {
        this.pozycje = pozycje;
    }
    
    
    
    
    
}
