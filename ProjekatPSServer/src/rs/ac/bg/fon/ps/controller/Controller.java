/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.operation.bibliotekar.VratiSveBilbiotekare;
import rs.ac.bg.fon.ps.operation.clan.ObrisiClana;
import rs.ac.bg.fon.ps.operation.clan.SacuvajClana;
import rs.ac.bg.fon.ps.operation.clan.VratiClana;
import rs.ac.bg.fon.ps.operation.clan.VratiPoImenuIPrezimenu;
import rs.ac.bg.fon.ps.operation.clan.VratiSveClanove;
import rs.ac.bg.fon.ps.operation.knjiga.DodajKnjigu;
import rs.ac.bg.fon.ps.operation.knjiga.IzmeniKnjigu;
import rs.ac.bg.fon.ps.operation.knjiga.ObrisiKnjigu;
import rs.ac.bg.fon.ps.operation.knjiga.VratiSveKnjige;
import rs.ac.bg.fon.ps.operation.pozajmica.RazduziSvePozajmice;
import rs.ac.bg.fon.ps.operation.pozajmica.SacuvajPozajmicu;
import rs.ac.bg.fon.ps.operation.pozajmica.VratiPozajmiceOdredjenogClana;
import rs.ac.bg.fon.ps.operation.pozajmica.VratiSvePozajmice;
import rs.ac.bg.fon.ps.operation.primerak.SacuvajPrimerak;
import rs.ac.bg.fon.ps.operation.primerak.VratiPrimerak;
import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.DBRepository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBClan;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBGeneric;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBKnjiga;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBPozajmica;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBPrimerak;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBUser;
import rs.ac.bg.fon.ps.thread.ProcessClientsRequests;

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
    private List<ProcessClientsRequests> nitiKlijenata;

    public Controller() {
        this.repositoryUser = new RepositoryDBUser();
        this.repositoryKnjiga = new RepositoryDBKnjiga();
        this.repositoryClan = new RepositoryDBClan();
        this.repositoryPrimerak = new RepositoryDBPrimerak();
        this.repositoryPozajmica = new RepositoryDBPozajmica();
        this.repositoryGeneric = new RepositoryDBGeneric();
        this.nitiKlijenata = new ArrayList<>();
    }

    public static Controller getInstance() {
        if (instanca == null) {
            instanca = new Controller();
        }
        return instanca;
    }

    public Bibliotekar login(String username, String password, ProcessClientsRequests klijentskaNit) throws Exception {
        Bibliotekar bibliotekar = new Bibliotekar();
        bibliotekar.setUsername(username);
        bibliotekar.setPassword(password);
        AbstractGenericOperation operation = new VratiSveBilbiotekare();
        operation.execute(bibliotekar);
        nitiKlijenata.add(klijentskaNit);
        return ((VratiSveBilbiotekare) operation).getBibliotekar();
    }

    public void addKnjiga(Knjiga knjiga) throws Exception {
        AbstractGenericOperation operation = new DodajKnjigu();
        operation.execute(knjiga);
    }

    public List<Knjiga> getAllBooks() throws Exception {
        AbstractGenericOperation operation = new VratiSveKnjige();
        operation.execute(new Knjiga());
        return ((VratiSveKnjige) operation).getKnjige();

    }

    public List<Clan> getAllClan() throws Exception {
        AbstractGenericOperation operation = new VratiSveClanove();
        operation.execute(new Clan());
        return ((VratiSveClanove) operation).getClanovi();
    }

    public void addClan(Clan c) throws Exception {
        AbstractGenericOperation operation = new SacuvajClana();
        operation.execute(c);
    }

    public boolean obrisiKnjigu(Knjiga knjiga) throws Exception {
        AbstractGenericOperation operation = new ObrisiKnjigu();
        operation.execute(knjiga);
        return true;
    }

    public void editKnjigu(Knjiga knjiga) throws Exception {
        AbstractGenericOperation operation = new IzmeniKnjigu();
        operation.execute(knjiga);

    }

    public boolean obrisiClana(Clan clan) throws Exception {
        AbstractGenericOperation operation = new ObrisiClana();
        operation.execute(clan);
        return true;
    }

    public boolean addPrimerak(Primerak primerak) throws Exception {
        AbstractGenericOperation operation = new SacuvajPrimerak();
        operation.execute(primerak);
        return true;
    }

    public boolean addPozajmica(Pozajmica pozajmica) throws Exception {
        AbstractGenericOperation operation = new SacuvajPozajmicu();
        operation.execute(pozajmica);
        return true;
    }

    public ArrayList<Pozajmica> getAllPozajmice() throws Exception {
        AbstractGenericOperation operation = new VratiSvePozajmice();
        operation.execute(new Pozajmica());
        return (ArrayList<Pozajmica>) ((VratiSvePozajmice) operation).getLista();
    }

    public void razduziSvePozajmice(ArrayList<Pozajmica> razduzivanje) throws Exception {
        AbstractGenericOperation operation = new RazduziSvePozajmice();
        operation.execute(razduzivanje);
    }

    public Clan getClan(Clan clan) throws Exception {
        AbstractGenericOperation operation = new VratiClana();
        operation.execute(clan);
        return ((VratiClana) operation).getClan();
    }

    public Primerak getPrimerak(Primerak primerak) throws Exception {
        AbstractGenericOperation operation = new VratiPrimerak();
        operation.execute(primerak);
        return ((VratiPrimerak) operation).getPrimerak();
    }

    public ArrayList<Clan> getAllClanUslov(Clan clan) throws Exception {
        AbstractGenericOperation operation = new VratiPoImenuIPrezimenu();
        operation.execute(clan);
        return (ArrayList<Clan>) ((VratiPoImenuIPrezimenu) operation).getClanovi();
    }

    public Object getAllPozajmiceUslov(Pozajmica p) throws Exception {
        AbstractGenericOperation operation = new VratiPozajmiceOdredjenogClana();
        operation.execute(p);
        return ((VratiPozajmiceOdredjenogClana) operation).getLista();
    }

    public void odjaviZaposlenog(ProcessClientsRequests klijentskaNit) {
        nitiKlijenata.remove(klijentskaNit);
        klijentskaNit.ugasiNit();
    }

    public void ugasiNiti() {
        for (ProcessClientsRequests processClientsRequests : nitiKlijenata) {
            processClientsRequests.ugasiNit();
        }
    }

}
