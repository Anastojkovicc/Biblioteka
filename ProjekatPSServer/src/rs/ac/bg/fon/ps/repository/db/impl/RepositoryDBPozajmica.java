/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.domain.Zanr;
import rs.ac.bg.fon.ps.repository.db.DBConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DBRepository;

/**
 *
 * @author ANA
 */
public class RepositoryDBPozajmica implements DBRepository<Pozajmica> {

    @Override
    public List<Pozajmica> getAll() {
        try {
            List<Pozajmica> pozajmice = new ArrayList<>();
            String sql = "SELECT * FROM pozajmica p join clan c on p.clan=c.brojClanskeKarte "
                    + "join primerak pr on p.primerak=pr.inventarskiBroj left join knjiga k ON pr.knjigaID=k.knjigaID";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
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
                Date datumI = rs.getDate("p.datumIzdavanja");
                Date datumV = rs.getDate("p.datumVracanja");
                Pozajmica pozajmica = new Pozajmica(pozajmicaID, datumI, datumV, clan, p);
                pozajmice.add(pozajmica);

            }
            rs.close();
            s.close();
            return pozajmice;

        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDBPozajmica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void add(Pozajmica param) throws Exception {
        String sql = "INSERT INTO pozajmica(datumIzdavanja,datumVracanja,clan,primerak) VALUES(?,?,?,?)";
        System.out.println(sql);
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDate(1, new Date(param.getDatumIzdavanja().getTime()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(param.getDatumIzdavanja());
        calendar.add(Calendar.MONTH, 6);
        java.util.Date datumV = calendar.getTime();
        ps.setDate(2, new Date(datumV.getTime()));
        ps.setInt(3, param.getClan().getBrojClanskeKarte());
        ps.setInt(4, param.getPrimerak().getInvertarskiBroj());
        ps.executeUpdate();
        ps.close();
        String sql2 = "UPDATE primerak SET izdat=1 WHERE inventarskiBroj=" + param.getPrimerak().getInvertarskiBroj();
        System.out.println(sql);
        Statement s = connection.createStatement();
        s.executeUpdate(sql2);
        s.close();

    }

    @Override
    public void edit(Pozajmica param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Pozajmica param) {
        try {
            String sql = "DELETE FROM pozajmica WHERE idPozajmice=" + param.getIdPozajmice();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement s = connection.createStatement();
            s.executeUpdate(sql);

            String sql2 = "UPDATE primerak SET izdat=0 WHERE inventarskiBroj=" + param.getPrimerak().getInvertarskiBroj();
            System.out.println(sql2);
            s.executeUpdate(sql2);
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDBPozajmica.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Pozajmica> getAllPoUslovu(Pozajmica param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pozajmica getUslov(Pozajmica param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
