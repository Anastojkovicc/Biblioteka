/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.domain.Zanr;
import rs.ac.bg.fon.ps.repository.db.DBConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DBRepository;

/**
 *
 * @author ANA
 */
public class RepositoryDBPrimerak implements DBRepository<Primerak> {

    @Override
    public List<Primerak> getAll() {

        try {
            String sql = "SELECT * FROM primerak p JOIN knjiga k ON p.knjigaID=k.knjigaID";
            List<Primerak> primerci = new ArrayList<>();
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
                p.setInvertarskiBroj(rs.getInt("p.inventarskiBroj"));
                p.setIzdat(rs.getBoolean("p.izdat"));
                p.setGodinaIzdanja(rs.getInt("p.godinaIzdanja"));
                p.setKnjiga(k);
                primerci.add(p);
            }
            rs.close();
            s.close();
            return primerci;
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDBPrimerak.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDBPrimerak.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void add(Primerak param) throws Exception {
        try {
            String sql = "INSERT INTO primerak (izdat,godinaIzdanja,knjigaID) VALUES (?,?,?)";
            System.out.println(sql);
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, param.isIzdat());
            ps.setInt(2, param.getGodinaIzdanja());
            ps.setInt(3, param.getKnjiga().getKnjigaID());
            ps.executeUpdate();

            ResultSet rsKey = ps.getGeneratedKeys();
            if (rsKey.next()) {
                int primerakID = rsKey.getInt(1);
                param.setInvertarskiBroj(primerakID);
            } else {
                throw new Exception("ID primerka nije generisan!");
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Primerak ne može biti sačuvan");
        }
    }

    @Override
    public void edit(Primerak param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Primerak param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Primerak> getAllPoUslovu(Primerak param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Primerak getUslov(Primerak param) throws Exception {
        Primerak primerak = null;
        try {
            String sql = "SELECT * FROM primerak p JOIN knjiga k ON p.knjigaID=k.knjigaID WHERE p.inventarskiBroj=" + param.getInvertarskiBroj() + " AND p.izdat=0";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Knjiga k = new Knjiga();
                k.setKnjigaID(rs.getInt("k.knjigaID"));
                k.setNaziv(rs.getString("k.naziv"));
                k.setAutor(rs.getString("k.autor"));
                k.setZanr(Zanr.valueOf(rs.getString("k.zanr")));
                primerak = new Primerak();
                primerak.setInvertarskiBroj(rs.getInt("p.inventarskiBroj"));
                primerak.setIzdat(rs.getBoolean("p.izdat"));
                primerak.setGodinaIzdanja(rs.getInt("p.godinaIzdanja"));
                primerak.setKnjiga(k);

            }
            rs.close();
            s.close();
            return primerak;
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDBPrimerak.class.getName()).log(Level.SEVERE, null, ex);
            return primerak;
        }
    }

    @Override
    public List<Primerak> getAll(Primerak param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void razduzi(Primerak param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
