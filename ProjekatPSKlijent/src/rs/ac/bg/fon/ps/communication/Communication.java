/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.domain.Primerak;

/**
 *
 * @author ANA
 */
public class Communication {

    Socket socket;
    Sender sender;
    Receiver receiver;
    private static Communication instance;

    private Communication() throws Exception {
        socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public Bibliotekar login(String username, String password) throws Exception {
        Bibliotekar bibliotekar = new Bibliotekar();
        bibliotekar.setUsername(username);
        bibliotekar.setPassword(password);
        Request request = new Request(Operation.LOGIN, bibliotekar);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Bibliotekar) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void addKnjiga(Knjiga knjiga) throws Exception {
        Request request = new Request(Operation.ADD_KNJIGA, knjiga);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public List<Knjiga> getAllBooks() throws Exception {

        Request request = new Request(Operation.GET_ALL_KNJIGA, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Knjiga>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Clan> getAllClan() throws Exception {
        Request request = new Request(Operation.GET_ALL_CLAN, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Clan>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void addClan(Clan c) throws Exception {
        Request request = new Request(Operation.ADD_CLAN, c);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            Clan noviClan = (Clan) response.getResult();
            c.setBrojClanskeKarte(noviClan.getBrojClanskeKarte());
        } else {
            throw response.getException();
        }
    }

    public boolean obrisiKnjigu(Knjiga knjiga) throws Exception {
        Request request = new Request(Operation.DELETE_KNJIGA, knjiga);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return true;
        } else {
            throw response.getException();
        }
    }

    public void editKnjigu(Knjiga knjiga) throws Exception {
        Request request = new Request(Operation.EDIT_KNJIGA, knjiga);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }

    }

    public boolean obrisiClana(Clan clan) throws Exception {
        Request request = new Request(Operation.DELETE_CLAN, clan);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return true;
        } else {
            throw response.getException();
        }

    }

    public boolean addPrimerak(Primerak primerak) throws Exception {
        Request request = new Request(Operation.ADD_PRIMERAK, primerak);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            Primerak primerakNovi = (Primerak) response.getResult();
            primerak.setInvertarskiBroj(primerakNovi.getInvertarskiBroj());
            return true;
        } else {
            throw response.getException();
        }
    }

    public boolean addPozajmica(Pozajmica pozajmica) throws Exception {
        Request request = new Request(Operation.ADD_POZAJMICA, pozajmica);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return true;
        } else {
            throw response.getException();
        }
    }

    public ArrayList<Pozajmica> getAllPozajmice() throws Exception {
        Request request = new Request(Operation.GET_ALL_POZAJMICE, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (ArrayList<Pozajmica>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public boolean obrisiPozajmicu(Pozajmica p) throws Exception {
        Request request = new Request(Operation.DELETE_POZAJMICA, p);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return true;
        } else {
            throw response.getException();
        }
    }

    public Clan getClan(Clan clan) throws Exception {
        Request request = new Request(Operation.GET_CLAN, clan);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Clan) response.getResult();
        } else {
            throw response.getException();
        }

    }

    public Primerak getPrimerak(Primerak primerak) throws Exception {
        Request request = new Request(Operation.GET_PRIMERAK, primerak);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Primerak) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public ArrayList<Clan> getAllClanUslov(Clan clan) throws Exception {
        Request request = new Request(Operation.GET_CLANOVI_USLOV, clan);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return  (ArrayList<Clan>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public ArrayList<Pozajmica> getAllPozajmicaUslov(Pozajmica p) throws Exception {
         Request request = new Request(Operation.GET_POZAJMICE_USLOV,p);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return   (ArrayList<Pozajmica>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public boolean razduziSve(ArrayList<Pozajmica> listaRazduzivanja) throws Exception {
        Request request = new Request(Operation.RAZDUZI_SVE, listaRazduzivanja);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return true;
        } else {
            throw response.getException();
        }
    }

}
