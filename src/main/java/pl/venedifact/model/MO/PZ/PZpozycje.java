/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.venedifact.model.MO.PZ;

import java.math.BigDecimal;

/**
 *
 * @author k.skowronski
 */
public class PZpozycje {
    
    
    private String nazwa;				     
    private String kod;
    private String vat;
    private String jm;
    private String asortyment;
    private String sww;
    private String pKWiU;
    private String ilosc;
    private String cena;
    private String wartosc;
    private String ileWOpak;
    private String cenaSp;
    private String towId;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getJm() {
        return jm;
    }

    public void setJm(String jm) {
        this.jm = jm;
    }

    public String getAsortyment() {
        return asortyment;
    }

    public void setAsortyment(String asortyment) {
        this.asortyment = asortyment;
    }

    public String getSww() {
        return sww;
    }

    public void setSww(String sww) {
        this.sww = sww;
    }

    public String getpKWiU() {
        return pKWiU;
    }

    public void setpKWiU(String pKWiU) {
        this.pKWiU = pKWiU;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getWartosc() {
        return wartosc;
    }

    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
    }

    public String getIleWOpak() {
        return ileWOpak;
    }

    public void setIleWOpak(String ileWOpak) {
        this.ileWOpak = ileWOpak;
    }

    public String getCenaSp() {
        return cenaSp;
    }

    public void setCenaSp(String cenaSp) {
        this.cenaSp = cenaSp;
    }

    public String getTowId() {
        return towId;
    }

    public void setTowId(String towId) {
        this.towId = towId;
    }
    
    public String toString() {
        return "nazwa=" + nazwa 
                + ", kod=" + kod 
                + ", vat='" + vat 
                + "', jm=" + jm
                + "', asortyment=" + asortyment
                + "', sww=" + sww
                + "', pKWiU=" + pKWiU
                + "', ilosc=" + ilosc
                + "', cena=" + cena
                + "', wartosc=" + wartosc
                + "', ileWOpak=" + ileWOpak
                + "', cenaSp=" + cenaSp
                + "', towId=" + towId
                ;
    }
    
    
    
    
    
    
}
