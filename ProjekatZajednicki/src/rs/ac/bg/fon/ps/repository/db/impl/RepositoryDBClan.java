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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.repository.db.DBConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DBRepository;
import rs.ac.bg.fon.ps.view.form.FrmPozajmica;

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
                clan.seteMail(rs.getString("email"));
                clan.setAdresa(rs.getString("adresa"));
                clan.setDatumClanarine(rs.getDate("datumUclanjenja"));
                clan.setDatumUclanjenja(rs.getDate("datumClanarine"));
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
            String sql = "INSERT INTO clan(ime,prezime,jmbg,telefon,email,adresa,datumUclanjenja,datumClanarine) VALUES(?,?,?,?,?,?,?,?)";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clan.getIme());
            ps.setString(2, clan.getPrezime());
            ps.setInt(3, clan.getJmbg());
            ps.setInt(4, clan.getTelefon());
            ps.setString(5, clan.geteMail());
            ps.setString(6, clan.getAdresa());
            ps.setDate(7, new java.sql.Date(clan.getDatumUclanjenja().getTime()));
            ps.setDate(8, new java.sql.Date(clan.getDatumClanarine().getTime()));
            ps.executeUpdate();
            ResultSet rsKey = ps.getGeneratedKeys();
            if (rsKey.next()) {
                int clanID = rsKey.getInt(1);
                clan.setBrojClanskeKarte(clanID);
            } else {
                throw new Exception("ID člana nije generisan!");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("Član ne može biti dodat");
        }

    }

    @Override
    public void edit(Clan clan) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
            String datum = sdf.format(clan.getDatumClanarine());
            String sql = "UPDATE clan SET ime='" + clan.getIme() + "', "
                    + "prezime='" + clan.getPrezime() + "', "
                    + "jmbg=" + clan.getJmbg() + ", "
                    + "telefon=" + clan.getTelefon() + ", "
                    + "adresa='" + clan.getAdresa() + "', "
                    + " datumClanarine='" + datum + "', "
                    + "WHERE id=" + clan.getBrojClanskeKarte();
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
            String sql = "DELETE FROM clan WHERE brojClanskeKarte=" + clan.getBrojClanskeKarte();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement s = connection.createStatement();
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska u dbu u brisanju clana");
        }
    }

    @Override
    public List<Clan> getAllPoUslovu(Clan param) throws Exception {
        //DODATI USLOV ZA DUGME PRETRAGE
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
                clan.seteMail(rs.getString("email"));
                clan.setAdresa(rs.getString("adresa"));
                clan.setDatumClanarine(rs.getDate("datumUclanjenja"));
                clan.setDatumUclanjenja(rs.getDate("datumClanarine"));
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
    public Clan getUslov(Clan param) throws Exception {
        Clan clan = null;
        try {

            String sql = "select * from clan where brojClanskeKarte=" + param.getBrojClanskeKarte() + " AND datumClanarine +INTERVAL '1' YEAR >= CURRENT_DATE";
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                clan = new Clan();
                clan.setBrojClanskeKarte(rs.getInt("brojClanskeKarte"));
                clan.setIme(rs.getString("ime"));
                clan.setPrezime(rs.getString("prezime"));
                clan.setJmbg(rs.getInt("jmbg"));
                clan.setTelefon(rs.getInt("telefon"));
                clan.seteMail(rs.getString("email"));
                clan.setAdresa(rs.getString("adresa"));
                clan.setDatumClanarine(rs.getDate("datumUclanjenja"));
                clan.setDatumUclanjenja(rs.getDate("datumClanarine"));
            }
            rs.close();
            s.close();
            return clan;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

}
