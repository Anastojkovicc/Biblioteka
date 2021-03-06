/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Receiver;
import rs.ac.bg.fon.ps.communication.Request;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.Sender;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.view.controller.MainController;

/**
 *
 * @author ANA
 */
public class ProcessClientsRequests extends Thread {

    Socket socket;
    private boolean state = false;

    public ProcessClientsRequests(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Sender sender = new Sender(socket);
        Receiver receiver = new Receiver(socket);

        while (!false) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();

                try {
                    switch (request.getOperacija()) {

                        case LOGIN:
                            Bibliotekar bibliotekar = (Bibliotekar) request.getArgument();
                            Bibliotekar ulogovani = Controller.getInstance().login(bibliotekar.getUsername(), bibliotekar.getPassword(), this);
                            MainController.getInstance().dodajUlogovanog(ulogovani);
                            response.setResult(ulogovani);
                            break;

                        case ADD_KNJIGA:
                            Knjiga knjiga = (Knjiga) request.getArgument();
                            Controller.getInstance().addKnjiga(knjiga);
                            break;

                        case GET_ALL_KNJIGA:
                            response.setResult(Controller.getInstance().getAllBooks());
                            break;

                        case GET_ALL_CLAN:
                            response.setResult(Controller.getInstance().getAllClan());
                            break;

                        case ADD_CLAN:
                            Clan clan = (Clan) request.getArgument();
                            Controller.getInstance().addClan(clan);
                            response.setResult(clan);
                            break;

                        case DELETE_KNJIGA:
                            Knjiga knjigaDelete = (Knjiga) request.getArgument();
                            Controller.getInstance().obrisiKnjigu(knjigaDelete);
                            break;

                        case EDIT_KNJIGA:
                            Knjiga knjigaEdit = (Knjiga) request.getArgument();
                            Controller.getInstance().editKnjigu(knjigaEdit);
                            break;

                        case DELETE_CLAN:
                            Clan clanDelete = (Clan) request.getArgument();
                            Controller.getInstance().obrisiClana(clanDelete);
                            break;

                        case ADD_PRIMERAK:
                            Primerak primerak = (Primerak) request.getArgument();
                            Controller.getInstance().addPrimerak(primerak);
                            response.setResult(primerak);
                            break;

                        case ADD_POZAJMICA:
                            Pozajmica pozajmica = (Pozajmica) request.getArgument();
                            Controller.getInstance().addPozajmica(pozajmica);
                            break;

                        case GET_ALL_POZAJMICE:
                            response.setResult(Controller.getInstance().getAllPozajmice());
                            break;

                        case GET_CLAN:
                            Clan clanGet = (Clan) request.getArgument();
                            Clan clanVracen = Controller.getInstance().getClan(clanGet);
                            response.setResult(clanVracen);
                            break;

                        case GET_PRIMERAK:
                            Primerak primerakGet = (Primerak) request.getArgument();
                            Primerak primerakVracen = Controller.getInstance().getPrimerak(primerakGet);
                            response.setResult(primerakVracen);
                            break;

                        case GET_CLANOVI_USLOV:
                            Clan clan1 = (Clan) request.getArgument();
                            response.setResult(Controller.getInstance().getAllClanUslov(clan1));
                            break;

                        case GET_POZAJMICE_USLOV:
                            Pozajmica p = (Pozajmica) request.getArgument();
                            response.setResult(Controller.getInstance().getAllPozajmiceUslov(p));
                            break;

                        case RAZDUZI_SVE:
                            ArrayList<Pozajmica> razduzivanje = (ArrayList<Pozajmica>) request.getArgument();
                            Controller.getInstance().razduziSvePozajmice(razduzivanje);
                            break;
                        case ODJAVI_ZAPOSLENOG:
                           Bibliotekar bibliotekarLogout =  (Bibliotekar) request.getArgument();
                            MainController.getInstance().izbaciOdjavljenog(bibliotekarLogout);
                            Controller.getInstance().odjaviZaposlenog(this);
                            break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ugasiNit() {
        state = true;
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Gasi se klijentska nit!");
        }
    }

}
