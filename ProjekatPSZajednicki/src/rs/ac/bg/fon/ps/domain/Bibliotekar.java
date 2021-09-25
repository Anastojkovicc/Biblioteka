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
public class Bibliotekar implements GenericEntity {

    private int bibliotekarID;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Bibliotekar() {
    }

    public Bibliotekar(int bibliotekarID, String ime, String prezime, String username, String password) {
        this.bibliotekarID = bibliotekarID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBibliotekarID() {
        return bibliotekarID;
    }

    public void setBibliotekarID(int bibliotekarID) {
        this.bibliotekarID = bibliotekarID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Bibliotekar{" + "ime=" + ime + ", prezime=" + prezime + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.bibliotekarID;
        hash = 11 * hash + Objects.hashCode(this.ime);
        hash = 11 * hash + Objects.hashCode(this.prezime);
        hash = 11 * hash + Objects.hashCode(this.username);
        hash = 11 * hash + Objects.hashCode(this.password);
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
        final Bibliotekar other = (Bibliotekar) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "bibliotekar";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "";
    }

    @Override
    public String getInsertValues() {
        return "";
    }

    @Override
    public void setId(int id) {
    }

    @Override
    public String getColumns() {
        return " * ";
    }

    @Override
    public String tableNameForGetAll() {
        return " bibliotekar ";
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
          List<GenericEntity> listaBibliotekara = new ArrayList<>();
        while (rs.next()) {
            Bibliotekar bibliotekar = new Bibliotekar(rs.getInt("bibliotekarID"),  rs.getString("ime"), rs.getString("prezime"),rs.getString("username"), rs.getString("password"));
            listaBibliotekara.add(bibliotekar);
        }
        return listaBibliotekara;
    }

    @Override
    public String getPoljaIZmene() {
        return "";
    }

    @Override
    public String getUslovBrisanja() {
        return "username='"+username+"' AND password='"+password+"'";
    }

    @Override
    public String getUslovPretrage() {
        return "";
    }

    @Override
    public String uslovZaNalazenje() {
        return "";
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws Exception {
        if (rs.next()) {
            return new Bibliotekar(rs.getInt("bibliotekarID"), rs.getString("ime"), rs.getString("prezime"), rs.getString("username"), rs.getString("password"));
        }
        throw new Exception("Sistem ne može da pronađe bibliotekara na osnovu učitanih vrednosti!");

    }

    @Override
    public String getKojaKnjiga() {
        return "";
        }

    @Override
    public String uslovZaClanarinu() {
        return "";
    }

}
