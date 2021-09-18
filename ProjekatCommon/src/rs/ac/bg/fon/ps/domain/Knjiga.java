/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.util.Objects;

/**
 *
 * @author ANA
 */
public class Knjiga {
    private int knjigaID;
    private String naziv;
    private String autor;
    private String zanr;
    private Primerak[] primerci;
   

    public Knjiga() {
    }

    public Knjiga(int knjigaID, String naziv, String autor, String zanr, Primerak[] primerci) {
        this.knjigaID = knjigaID;
        this.naziv = naziv;
        this.autor = autor;
        this.zanr = zanr;
        this.primerci = primerci;
    }

    
    @Override
    public String toString() {
        return "Knjiga{" + "naziv=" + naziv + ", autor=" + autor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.knjigaID;
        hash = 37 * hash + Objects.hashCode(this.naziv);
        hash = 37 * hash + Objects.hashCode(this.autor);
        hash = 37 * hash + Objects.hashCode(this.zanr);
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
        final Knjiga other = (Knjiga) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        return true;
    }

    public int getKnjigaID() {
        return knjigaID;
    }

    public void setKnjigaID(int knjigaID) {
        this.knjigaID = knjigaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public Primerak[] getPrimerci() {
        return primerci;
    }

    public void setPrimerci(Primerak[] primerci) {
        this.primerci = primerci;
    }

    

    
    
    
}