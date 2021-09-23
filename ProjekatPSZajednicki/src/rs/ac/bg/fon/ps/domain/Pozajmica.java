/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ANA
 */
public class Pozajmica implements Serializable{
    private int idPozajmice;
    private Date datumIzdavanja;
    private Date datumVracanja;
    private Clan clan;
    private Primerak primerak;
   
    public Pozajmica() {
    }

    public Pozajmica(int idPozajmice, Date datumIzdavanja, Date datumVracanja, Clan clan, Primerak primerak) {
        this.idPozajmice = idPozajmice;
        this.datumIzdavanja = datumIzdavanja;
        this.datumVracanja = datumVracanja;
        this.clan = clan;
        this.primerak = primerak;
    }

        

    public Primerak getPrimerak() {
        return primerak;
    }

    public void setPrimerak(Primerak primerak) {
        this.primerak = primerak;
    }

    public int getIdPozajmice() {
        return idPozajmice;
    }

    public void setIdPozajmice(int idPozajmice) {
        this.idPozajmice = idPozajmice;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }
    
}
