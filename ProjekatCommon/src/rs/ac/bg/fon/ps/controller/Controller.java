/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.repository.RepositoryClan;
import rs.ac.bg.fon.ps.repository.RepositoryKnjiga;
import rs.ac.bg.fon.ps.repository.RepositoryUser;

/**
 *
 * @author ANA
 */
public class Controller {

    private final RepositoryUser repositoryUser;
    private final RepositoryKnjiga repositoryKnjiga;
    private final RepositoryClan repositoryClan;
    private static Controller instanca;

    public int idKnjige=0;
    public int idClana=0;
    
    private Controller() {
        this.repositoryUser = new RepositoryUser();
        this.repositoryKnjiga = new RepositoryKnjiga();
        this.repositoryClan = new RepositoryClan();
    }
    
    public static Controller getInstance() {
        if(instanca==null){
        instanca= new Controller();
        }
        return instanca;
    }
    
    public Bibliotekar login(String username, String password) throws Exception{
        List<Bibliotekar> bibliotekari = repositoryUser.getBibliotekari();
        for (Bibliotekar bibliotekar : bibliotekari) {
            if(bibliotekar.getUsername().equals(username) && bibliotekar.getPassword().equals(password)){
                return bibliotekar;
            }
        }
       throw new Exception("Unknown user!");
    }
    
    public void addKnjiga(Knjiga knjiga){
        repositoryKnjiga.add(knjiga);
    }

    public List<Knjiga> getAllBooks() {
        return repositoryKnjiga.getListaKnjiga();
    }

    public int vratiID() {
        return ++idKnjige;
    }

    public List<Clan> getAllClan() {
        return repositoryClan.getClanovi();
    }
    
    public void addClan(Clan c){
        repositoryClan.add(c);
    }

    public int vratiIDClana() {
        return ++idClana;
    }

    public void obrisiKnjigu(Knjiga knjiga) throws Exception {
        repositoryKnjiga.obrisi(knjiga);
    }
}
