/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ANA
 */
public class Clan implements GenericEntity {

    private int brojClanskeKarte;
    private String ime;
    private String prezime;
    private int jmbg;
    private int telefon;
    private String eMail;
    private String adresa;
    private Date datumUclanjenja;
    private Date datumClanarine;

    public Clan() {
    }

    public Clan(int brojClanskeKarte, String ime, String prezime, int jmbg, int telefon, String eMail, String adresa, Date datumUclanjenja, Date datumClanarine) {
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

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public void setJmbg(int jmbg) {
        this.jmbg = jmbg;
    }

    public int getTelefon() {
        return telefon;
    }

    public int getJmbg() {
        return jmbg;
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

    @Override
    public String getTableName() {
        return "clan";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "ime,prezime,jmbg,telefon,email,adresa,datumUclanjenja,datumClanarine";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        java.sql.Date datumUclanjenjaSql = new java.sql.Date(datumUclanjenja.getTime());

        sb.append("'").append(ime).append("', ")
                .append("'").append(prezime).append("', ")
                .append(jmbg).append(", ")
                .append(telefon).append(", ")
                .append("'").append(eMail).append("', ")
                .append("'").append(adresa).append("', ")
                .append("'").append(datumUclanjenjaSql).append("', ")
                .append("'").append(datumUclanjenjaSql).append("' ");
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.brojClanskeKarte = id;
    }

    @Override
    public String getColumns() {
        return "*";
    }

    @Override
    public String tableNameForGetAll() {
        return "clan";
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        while (rs.next()) {
            Clan clan = new Clan();

            clan.setBrojClanskeKarte(rs.getInt("brojClanskeKarte"));
            clan.setIme(rs.getString("ime"));
            clan.setPrezime(rs.getString("prezime"));
            clan.setJmbg(rs.getInt("jmbg"));
            clan.setTelefon(rs.getInt("telefon"));
            clan.seteMail(rs.getString("email"));
            clan.setAdresa(rs.getString("adresa"));
            clan.setDatumClanarine(rs.getDate("datumUclanjenja"));
            clan.setDatumUclanjenja(rs.getDate("datumClanarine"));

            lista.add(clan);
        }
        return lista;
    }

    @Override
    public String getPoljaIZmene() {
        return "";
    }

    @Override
    public String getUslovBrisanja() {
        return " brojClanskeKarte=" + brojClanskeKarte;
    }

    @Override
    public String getUslovPretrage() {
        return " ime like '%" + ime + "%' OR prezime like '%" + prezime + "%' ";
    }

    @Override
    public String uslovZaNalazenje() {
        return  "brojClanskeKarte=" + brojClanskeKarte + " AND datumClanarine +INTERVAL '1' YEAR >= CURRENT_DATE";
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws Exception {
        if (rs.next()) {
            Clan clan = new Clan();

            clan.setBrojClanskeKarte(rs.getInt("brojClanskeKarte"));
            clan.setIme(rs.getString("ime"));
            clan.setPrezime(rs.getString("prezime"));
            clan.setJmbg(rs.getInt("jmbg"));
            clan.setTelefon(rs.getInt("telefon"));
            clan.seteMail(rs.getString("email"));
            clan.setAdresa(rs.getString("adresa"));
            clan.setDatumClanarine(rs.getDate("datumUclanjenja"));
            clan.setDatumUclanjenja(rs.getDate("datumClanarine"));
            return clan;
        }
        throw new Exception("Ne postoji klijent sa unetim podacima!");

    }

   
}
