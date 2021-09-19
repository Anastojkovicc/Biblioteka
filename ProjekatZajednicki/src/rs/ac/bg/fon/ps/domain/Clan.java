/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ANA
 */
public class Clan {
    private int brojClanskeKarte;
    private String ime;
    private String prezime;
    private String jmbg;
    private String telefon;
    private String eMail;
    private String adresa;
    private Date datumUclanjenja;
    private Date datumClanarine;

    public Clan() {
    }

    public Clan(int brojClanskeKarte, String ime, String prezime, String jmbg, String telefon, String eMail, String adresa, Date datumUclanjenja, Date datumClanarine) {
        this.brojClanskeKarte = brojClanskeKarte;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.telefon = telefon;
        this.eMail = eMail;
        this.adresa = adresa;
        this.datumUclanjenja = datumUclanjenja;
        this.datumClanarine = datumClanarine;
    }

 

   

    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public void setDatumUclanjenja(Date datumUclanjenja) {
        this.datumUclanjenja = datumUclanjenja;
    }

    public int getBrojClanskeKarte() {
        return brojClanskeKarte;
    }

    public void setBrojClanskeKarte(int brojClanskeKarte) {
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDatumClanarine() {
        return datumClanarine;
    }

    public void setDatumClanarine(Date datumClanarine) {
        this.datumClanarine = datumClanarine;
    }

    
    
    

    @Override
    public String toString() {
        return "Clan{" + "ime=" + ime + ", prezime=" + prezime + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.brojClanskeKarte;
        hash = 23 * hash + Objects.hashCode(this.ime);
        hash = 23 * hash + Objects.hashCode(this.prezime);
        hash = 23 * hash + Objects.hashCode(this.jmbg);
        hash = 23 * hash + Objects.hashCode(this.telefon);
        hash = 23 * hash + Objects.hashCode(this.eMail);
        hash = 23 * hash + Objects.hashCode(this.adresa);
        hash = 23 * hash + Objects.hashCode(this.datumUclanjenja);
        hash = 23 * hash + Objects.hashCode(this.datumClanarine);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clan other = (Clan) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return true;
    }
    
    
}
