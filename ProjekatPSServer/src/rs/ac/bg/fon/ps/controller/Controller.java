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
import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.operation.bibliotekar.GetAllBibliotekari;
import rs.ac.bg.fon.ps.operation.knjiga.AddKnjiga;
import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.DBRepository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBClan;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBGeneric;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBKnjiga;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBPozajmica;
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
    private final Repository repositoryPozajmica;
    private final Repository repositoryGeneric;
    private static Controller instanca;

    public Controller() {
        this.repositoryUser = new RepositoryDBUser();
        this.repositoryKnjiga = new RepositoryDBKnjiga();
        this.repositoryClan = new RepositoryDBClan();
        this.repositoryPrimerak = new RepositoryDBPrimerak();
        this.repositoryPozajmica = new RepositoryDBPozajmica();
        this.repositoryGeneric = new RepositoryDBGeneric();
    }

    public static Controller getInstance() {
        if (instanca == null) {
            instanca = new Controller();
        }
        return instanca;
    }

    public Bibliotekar login(String username, String password) throws Exception {
        Bibliotekar bibliotekar = new Bibliotekar();
        bibliotekar.setUsername(username);
        bibliotekar.setPassword(password);
        AbstractGenericOperation operation = new GetAllBibliotekari();
        operation.execute(bibliotekar);
        return ((GetAllBibliotekari) operation).getBibliotekar();
    }

    public void addKnjiga(Knjiga knjiga) throws Exception {
//        AbstractGenericOperation operation = new AddKnjiga();
//        operation.execute(knjiga);
        ((DBRepository) repositoryGeneric).connect();
        try {
            repositoryGeneric.add(knjiga);
            ((DBRepository) repositoryGeneric).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
    }

    public List<Knjiga> getAllBooks() throws Exception {
        List<Knjiga> knjige = null;
        ((DBRepository) repositoryGeneric).connect();
        try {
            Knjiga knjiga= new Knjiga();
            knjige = repositoryGeneric.getAll(knjiga);
            ((DBRepository) repositoryGeneric).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
        return knjige;
    }

    public List<Clan> getAllClan() throws Exception {
        List<Clan> clanovi = new ArrayList<>();
        ((DBRepository) repositoryGeneric).connect();
        try {
            Clan clan= new Clan();
            clanovi = repositoryGeneric.getAll(clan);
            ((DBRepository) repositoryGeneric).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
        return clanovi;
    }

    public void addClan(Clan c) throws Exception {
        ((DBRepository) repositoryGeneric).connect();
        try {
            repositoryGeneric.add(c);
            ((DBRepository) repositoryGeneric).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
    }

    public boolean obrisiKnjigu(Knjiga knjiga) throws Exception {
        ((DBRepository) repositoryGeneric).connect();
        try {
            repositoryGeneric.delete(knjiga);
            ((DBRepository) repositoryGeneric).commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
    }

    public void editKnjigu(Knjiga knjiga) throws Exception {
        ((DBRepository) repositoryGeneric).connect();
        try {
            ((DBRepository) repositoryGeneric).edit(knjiga);
            ((DBRepository) repositoryGeneric).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryKnjiga).disconnect();
        }

    }

    public boolean obrisiClana(Clan clan) throws Exception {
        ((DBRepository) repositoryGeneric).connect();
        try {
            repositoryGeneric.delete(clan);
            ((DBRepository) repositoryGeneric).commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }

    }

    public boolean addPrimerak(Primerak primerak) throws Exception {
        ((DBRepository) repositoryGeneric).connect();
        try {
            repositoryGeneric.add(primerak);
            ((DBRepository) repositoryGeneric).commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
    }

    public boolean addPozajmica(Pozajmica pozajmica) throws Exception {
        ((DBRepository) repositoryGeneric).connect();
        try {
            repositoryGeneric.add(pozajmica);
            ((DBRepository) repositoryGeneric).commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
    }

    public ArrayList<Pozajmica> getAllPozajmice() throws Exception {
        ArrayList<Pozajmica> pozajmice = new ArrayList<>();
        ((DBRepository) repositoryGeneric).connect();
        try {
            Pozajmica pozajmica= new Pozajmica();
            pozajmice = (ArrayList<Pozajmica>) repositoryGeneric.getAll(pozajmica);
            ((DBRepository) repositoryGeneric).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
        return pozajmice;
    }

    public boolean obrisiPozajmicu(Pozajmica p) throws Exception {
        ((DBRepository) repositoryGeneric).connect();
        try {
            repositoryGeneric.delete(p);
            ((DBRepository) repositoryGeneric).commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }

    }

    public Clan getClan(Clan clan) throws Exception {
        Clan clan2 = null;
        ((DBRepository) repositoryGeneric).connect();
        try {
            clan2 = (Clan) repositoryGeneric.getUslov(clan);
            ((DBRepository) repositoryGeneric).commit();
            return clan2;

        } catch (Exception e) {
            ((DBRepository) repositoryGeneric).rollback();
            return clan2;

        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }

    }

    public Primerak getPrimerak(Primerak primerak) throws Exception {
        Primerak prim = null;
        ((DBRepository) repositoryGeneric).connect();
        try {
            prim = (Primerak) repositoryGeneric.getUslov(primerak);
            ((DBRepository) repositoryGeneric).commit();
            return prim;
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            return prim;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
    }

    public ArrayList<Clan> getAllClanUslov(Clan clan) throws Exception {
        ArrayList<Clan> clanovi = new ArrayList<>();
        ((DBRepository) repositoryGeneric).connect();
        try {
            clanovi = (ArrayList<Clan>) repositoryGeneric.getAllPoUslovu(clan);
            ((DBRepository) repositoryGeneric).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
        return clanovi;
    }

    public Object getAllPozajmiceUslov(Pozajmica p) throws Exception {
        ArrayList<Pozajmica> pozajmice = new ArrayList<>();
        ((DBRepository) repositoryGeneric).connect();
        try {
            pozajmice = (ArrayList<Pozajmica>) repositoryGeneric.getAllPoUslovu(p);
            ((DBRepository) repositoryGeneric).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DBRepository) repositoryGeneric).rollback();
            throw e;
        } finally {
            ((DBRepository) repositoryGeneric).disconnect();
        }
        return pozajmice;
    }

    public void razduziSvePozajmice(ArrayList<Pozajmica> razduzivanje) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
