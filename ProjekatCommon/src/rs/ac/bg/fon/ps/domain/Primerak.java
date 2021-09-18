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
public class Primerak {
    private int invertarskiBroj;
    private boolean izdat;
    private Date godinaIzdanja;

    public Primerak() {
    }

    public Primerak(int invertarskiBroj, boolean izdat, Date godinaIzdanja) {
        this.invertarskiBroj = invertarskiBroj;
        this.izdat = izdat;
        this.godinaIzdanja = godinaIzdanja;
    }

 

   

    public Date getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(Date godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public int getInvertarskiBroj() {
        return invertarskiBroj;
    }

    public void setInvertarskiBroj(int invertarskiBroj) {
        this.invertarskiBroj = invertarskiBroj;
    }

    

    public boolean isIzdat() {
        return izdat;
    }

    public void setIzdat(boolean izdat) {
        this.izdat = izdat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.invertarskiBroj;
        hash = 29 * hash + (this.izdat ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.godinaIzdanja);
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
        final Primerak other = (Primerak) obj;
        return true;
    }
    
    
    
}
