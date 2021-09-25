/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ANA
 */
public class Primerak implements GenericEntity {

    private int invertarskiBroj;
    private boolean izdat;
    private int godinaIzdanja;
    private Knjiga knjiga;

    public Primerak() {
    }

    public Primerak(int invertarskiBroj, boolean izdat, int godinaIzdanja, Knjiga knjiga) {
        this.invertarskiBroj = invertarskiBroj;
        this.izdat = izdat;
        this.godinaIzdanja = godinaIzdanja;
        this.knjiga = knjiga;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public int getInvertarskiBroj() {
        return invertarskiBroj;
    }

    public void setInvertarskiBroj(int invertarskiBroj) {
        this.invertarskiBroj = invertarskiBroj;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public Knjiga getKnjiga() {
        return knjiga;
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

    @Override
    public String getTableName() {
        return "primerak";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "izdat,godinaIzdanja,knjigaID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(izdat).append(", ").append(godinaIzdanja).append(", ").append(knjiga.getKnjigaID());
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.invertarskiBroj = id;
    }

    @Override
    public String getColumns() {
        return "*";
    }

    @Override
    public String tableNameForGetAll() {
        return "primerak p JOIN knjiga k ON p.knjigaID=k.knjigaID";

    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        while (rs.next()) {
            Knjiga k = new Knjiga();
            k.setKnjigaID(rs.getInt("k.knjigaID"));
            k.setNaziv(rs.getString("k.naziv"));
            k.setAutor(rs.getString("k.autor"));
            k.setZanr(Zanr.valueOf(rs.getString("k.zanr")));
            Primerak p = new Primerak();
            p.setInvertarskiBroj(rs.getInt("p.inventarskiBroj"));
            p.setIzdat(rs.getBoolean("p.izdat"));
            p.setGodinaIzdanja(rs.getInt("p.godinaIzdanja"));
            p.setKnjiga(k);
            lista.add(p);
        }
        return lista;
    }

    @Override
    public String getPoljaIZmene() {
        return "izdat=" + isIzdat();
    }

    @Override
    public String getUslovBrisanja() {
        return "inventarskiBroj=" + invertarskiBroj + " AND izdat=0";

    }

    @Override
    public String getUslovPretrage() {
        return " p.inventarskiBroj=" + invertarskiBroj + " AND p.izdat=0";
    }

    @Override
    public String uslovZaNalazenje() {
        return "";
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws Exception {
        if (rs.next()) {
            Knjiga k = new Knjiga();
            k.setKnjigaID(rs.getInt("k.knjigaID"));
            k.setNaziv(rs.getString("k.naziv"));
            k.setAutor(rs.getString("k.autor"));
            k.setZanr(Zanr.valueOf(rs.getString("k.zanr")));
            Primerak p = new Primerak();
            p.setInvertarskiBroj(rs.getInt("p.inventarskiBroj"));
            p.setIzdat(rs.getBoolean("p.izdat"));
            p.setGodinaIzdanja(rs.getInt("p.godinaIzdanja"));
            p.setKnjiga(k);
            return p;
        }
        //throw new Exception("Ne postoji primerak sa unetim podacima!");
        return null;
    }

    @Override
    public String getKojaKnjiga() {
        return " inventarskiBroj=" + invertarskiBroj;
    }

    @Override
    public String uslovZaClanarinu() {
        return "";
    }

}
