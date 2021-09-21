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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.repository.db.DBConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DBRepository;

/**
 *
 * @author ANA
 */
public class RepositoryDBClan implements DBRepository<Clan> {
    
    @Override
    public List<Clan> getAll() {
        try {
            String sql = "select * from clan";
            List<Clan> clanovi = new ArrayList<>();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Clan clan = new Clan();
                clan.setBrojClanskeKarte(rs.getInt("brojClanskeKarte"));
                clan.setIme(rs.getString("ime"));
                clan.setPrezime(rs.getString("prezime"));
                clan.setJmbg(rs.getInt("jmbg"));
                clan.setTelefon(rs.getInt("telefon"));
                clan.setAdresa(rs.getString("adresa"));
                //datum
                clan.setDatumClanarine(new Date());
                clan.setDatumUclanjenja(new Date());
                clanovi.add(clan);
                
            }
            rs.close();
            s.close();
            return clanovi;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        
    }
    
    @Override
    public void add(Clan clan) throws Exception {
        try {
            String sql = "INSERT INTO clan VALUES(?,?,?,?,?,?,?,?)";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(2, clan.getIme());
            ps.setString(3, clan.getPrezime());
            ps.setInt(4, clan.getJmbg());
            ps.setInt(5, clan.getTelefon());
            ps.setString(6, clan.geteMail());
            ps.setString(7, clan.getAdresa());
            ps.setDate(8, new java.sql.Date(clan.getDatumUclanjenja().getTime()));
            ps.setDate(9, new java.sql.Date(clan.getDatumClanarine().getTime()));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Clan ne moze biti dodat");
        }
        
    }
    
    @Override
    public void edit(Clan clan) throws Exception {
        try {
            String sql = "UPDATE clan SET ime='" + clan.getIme() + "', "
                    + "prezime='" + clan.getPrezime() + "', "
                    + "jmbg=" + clan.getJmbg() + ", "
                    + "telefon=" + clan.getTelefon() + ", "
                    + "adresa='" + clan.getAdresa() + "' "
                    + "WHERE id=" + clan.getBrojClanskeKarte();
            //dodati datum za izmenu
            System.out.println(sql);
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement s = connection.createStatement();
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("DB greska u azuriranju clana");
        }
        
    }
    
    @Override
    public void delete(Clan clan) throws Exception {
        try {
            String sql = "DELETE FROM clan WHERE id=" + clan.getBrojClanskeKarte();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement s = connection.createStatement();
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska u dbu u brisanju clana");
        }
    }
    
}
