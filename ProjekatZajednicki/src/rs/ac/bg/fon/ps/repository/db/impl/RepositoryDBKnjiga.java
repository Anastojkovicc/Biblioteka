/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.domain.Zanr;
import rs.ac.bg.fon.ps.repository.db.DBConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DBRepository;

/**
 *
 * @author ANA
 */
public class RepositoryDBKnjiga implements DBRepository<Knjiga> {

    @Override
    public List<Knjiga> getAll() {
        try {
            String sql = "SELECT * FROM knjiga";
            
            List<Knjiga> knjige = new ArrayList<>();
            Connection connection= DBConnectionFactory.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Knjiga k = new Knjiga();
                k.setKnjigaID(rs.getInt("knjigaID"));
                k.setNaziv(rs.getString("naziv"));
                k.setAutor(rs.getString("autor"));
                k.setZanr( Zanr.valueOf(rs.getString("zanr")));
                k.setPrimerci(null);
                knjige.add(k);
            }
            rs.close();
            s.close();
            return knjige;
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDBKnjiga.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public void add(Knjiga param) throws Exception {
        try{
        String sql = "INSERT INTO knjiga (naziv,autor,zanr) VALUES (?,?,?)";
        Connection connection= DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps= connection.prepareStatement(sql);
        ps.setString(1, param.getNaziv());
        ps.setString(2, param.getAutor());
        ps.setString(3, param.getZanr().toString());
        ps.executeUpdate();
        ps.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            throw  new Exception("Knjiga ne moze biti sacuvana");
        }
    }

    @Override
    public void edit(Knjiga knjiga) throws Exception {
        try {
            String sql= "UPDATE naziv='"+knjiga.getNaziv()+"', "
                    + "autor='"+knjiga.getAutor()+"', "
                    + "zanr='"+knjiga.getZanr()+"' "
                    + "WHERE knjigaID="+knjiga.getKnjigaID();
            System.out.println(sql);
            Connection connection= DBConnectionFactory.getInstance().getConnection();
            Statement s= connection.createStatement();
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska u dbu u izmeni knjige");
        }
    }

    @Override
    public void delete(Knjiga knjiga) throws Exception {
        try {
            String sql= "DELETE FROM knjiga WHERE id="+knjiga.getKnjigaID();
            System.out.println(sql);
            Connection connection= DBConnectionFactory.getInstance().getConnection();
            Statement s= connection.createStatement();
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("DB greska u brisanju knjige");
        }
        
    }

 

}
