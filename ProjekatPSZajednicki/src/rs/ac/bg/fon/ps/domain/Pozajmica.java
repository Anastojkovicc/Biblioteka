/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ANA
 */
public class Pozajmica implements GenericEntity {

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

    @Override
    public String getTableName() {
        return "pozajmica";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "datumIzdavanja,datumVracanja,clan,primerak";
    }

    @Override
    public String getInsertValues() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datumIzdavanja);
        calendar.add(Calendar.MONTH, 6);
        java.util.Date datumV = calendar.getTime();
        datumVracanja = datumV;
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(new java.sql.Date(datumIzdavanja.getTime())).append("', ")
           .append("'").append(new java.sql.Date(datumVracanja.getTime())).append("', ")
                .append(clan.getBrojClanskeKarte()).append(", ")
                .append(primerak.getInvertarskiBroj());
        return sb.toString();
    }

    @Override
    public void setId(int id) {
    }

    @Override
    public String getColumns() {
        return "*";
    }

    @Override
    public String tableNameForGetAll() {
        return "pozajmica p join clan c on p.clan=c.brojClanskeKarte "
                + "join primerak pr on p.primerak=pr.inventarskiBroj left join knjiga k ON pr.knjigaID=k.knjigaID";
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
            p.setInvertarskiBroj(rs.getInt("pr.inventarskiBroj"));
            p.setIzdat(rs.getBoolean("pr.izdat"));
            p.setGodinaIzdanja(rs.getInt("pr.godinaIzdanja"));
            p.setKnjiga(k);
            Clan clan = new Clan();
            clan.setBrojClanskeKarte(rs.getInt("c.brojClanskeKarte"));
            clan.setIme(rs.getString("c.ime"));
            clan.setPrezime(rs.getString("c.prezime"));
            clan.setJmbg(rs.getInt("c.jmbg"));
            clan.setTelefon(rs.getInt("c.telefon"));
            clan.seteMail(rs.getString("c.email"));
            clan.setAdresa(rs.getString("c.adresa"));
            clan.setDatumClanarine(rs.getDate("c.datumUclanjenja"));
            clan.setDatumUclanjenja(rs.getDate("c.datumClanarine"));
            int pozajmicaID = rs.getInt("p.idPozajmice");
            java.sql.Date datumI = rs.getDate("p.datumIzdavanja");
            java.sql.Date datumV = rs.getDate("p.datumVracanja");
            Pozajmica pozajmica = new Pozajmica(pozajmicaID, datumI, datumV, clan, p);
            lista.add(pozajmica);

        }
        return lista;
    }

    @Override
    public String getPoljaIZmene() {
        return "";
    }

    @Override
    public String getUslovBrisanja() {
        return " idPozajmice=" + idPozajmice;
    }

    @Override
    public String getUslovPretrage() {
        return "c.brojClanskeKarte=" + clan.getBrojClanskeKarte();
    }

    @Override
    public String uslovZaNalazenje() {
        return "";
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public String getKojaKnjiga() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String uslovZaClanarinu() {
        return "";
    }

}
