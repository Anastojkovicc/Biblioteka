/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.RepositoryClan;
import rs.ac.bg.fon.ps.repository.RepositoryKnjiga;
import rs.ac.bg.fon.ps.repository.RepositoryUser;
import rs.ac.bg.fon.ps.repository.db.DBRepository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBClan;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBKnjiga;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBPrimerak;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBUser;

/**
 *
 * @author ANA
 */
public class Controller {

    private final Repository repositoryUser;
    private final Repository repositoryKnjiga;
    private final Repository repositoryClan;
    private final Repository repositoryPrimerak;
    private static Controller instanca;

    public int idKnjige = 0;
    public int idClana = 0;

    public Controller() {
        this.repositoryUser = new RepositoryDBUser();
        this.repositoryKnjiga = new RepositoryDBKnjiga();
        this.repositoryClan = new RepositoryDBClan();
        this.repositoryPrimerak=  new RepositoryDBPrimerak();
    }

    public static Controller getInstance() {
        if (instanca == null) {
            instanca = new Controller();
        }
        return instanca;
    }

    public Bibliotekar login(String username, String password) throws Exception {
        List<Bibliotekar> bibliotekari = repositoryUser.getAll();
        for (Bibliotekar bibliotekar : bibliotekari) {
            if (bibliotekar.getUsername().equals(username) && bibliotekar.getPassword().equals(password)) {
                return bibliotekar;
            }
        }
        throw new Exception("Unknown user!");
    }

    public void addKnjiga(Knjiga knjiga) throws Exception {
        ((DBRepository) repositoryKnjiga).connect();
        try {
            repositoryKnjiga.add(knjiga);
            ((DBRepository) repositoryKnjiga).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryKnjiga).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryKnjiga).disconnect();
        }
    }

    public List<Knjiga> getAllBooks() throws Exception {
        List<Knjiga> knjige = null;
        ((DBRepository) repositoryKnjiga).connect();
        try {
            knjige = repositoryKnjiga.getAll();
            ((DBRepository) repositoryKnjiga).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryKnjiga).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryKnjiga).disconnect();
        }
        return knjige;
    }

    public int vratiID() {
        return ++idKnjige;
    }

    public List<Clan> getAllClan() throws Exception {
        List<Clan> clanovi = new ArrayList<>();
        ((DBRepository) repositoryClan).connect();
        try {
            clanovi = repositoryClan.getAll();
            ((DBRepository) repositoryClan).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryClan).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryClan).disconnect();
        }
        return clanovi;
    }

    public void addClan(Clan c) throws Exception {
        ((DBRepository) repositoryClan).connect();
        try {
            repositoryClan.add(c);
            ((DBRepository) repositoryClan).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryClan).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryClan).disconnect();
        }
    }

    public int vratiIDClana() {
        return ++idClana;
    }

    public void obrisiKnjigu(Knjiga knjiga) throws Exception {
        ((DBRepository) repositoryKnjiga).connect();
        try {
            repositoryKnjiga.delete(knjiga);
            ((DBRepository) repositoryKnjiga).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryKnjiga).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryKnjiga).disconnect();
        }
    }

    public void editKnjigu(Knjiga knjiga) throws Exception {
        ((DBRepository) repositoryKnjiga).connect();
        try {
            ((DBRepository) repositoryKnjiga).edit(knjiga);
            ((DBRepository) repositoryKnjiga).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryKnjiga).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryKnjiga).disconnect();
        }

    }

    public boolean obrisiClana(Clan clan) throws Exception {
        ((DBRepository) repositoryClan).connect();
        try {
            repositoryClan.delete(clan);
            ((DBRepository) repositoryClan).commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryClan).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryClan).disconnect();
        }

    }

    public boolean addPrimerak(Primerak primerak) throws Exception {
        ((DBRepository) repositoryPrimerak).connect();
        try {
            repositoryPrimerak.add(primerak);
            ((DBRepository) repositoryPrimerak).commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryPrimerak).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryPrimerak).disconnect();
        }
    }

}
