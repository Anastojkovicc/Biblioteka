/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.repository.db.DBConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DBRepository;

/**
 *
 * @author ANA
 */
public class RepositoryDBUser implements DBRepository<Bibliotekar>{

    @Override
    public List<Bibliotekar> getAll() {
        try {
            String sql = "SELECT * FROM bibliotekar";
           
            List<Bibliotekar> bibliotekari = new ArrayList<>();
            Connection connection= DBConnectionFactory.getInstance().getConnection();
            Statement s =  connection.createStatement();
            ResultSet rs= s.executeQuery(sql);
            while(rs.next()){
                Bibliotekar b = new Bibliotekar();
                b.setBibliotekarID(rs.getInt("bibliotekarID"));
                b.setIme(rs.getString("ime"));
                b.setPrezime(rs.getString("prezime"));
                b.setUsername(rs.getString("username"));
                b.setPassword(rs.getString("password"));
                bibliotekari.add(b);
            }
            rs.close();
            s.close();
            return bibliotekari;
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDBUser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDBUser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
      
    }

    @Override
    public void add(Bibliotekar param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Bibliotekar param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Bibliotekar param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bibliotekar> getAllPoUslovu(Bibliotekar param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bibliotekar getUslov(Bibliotekar param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bibliotekar> getAll(Bibliotekar param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void razduzi(Bibliotekar param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
