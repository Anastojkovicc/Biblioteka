/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
import rs.ac.bg.fon.ps.thread.ProcessClientsRequests;

/**
 *
 * @author ANA
 */
public class Server {

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Čekanje konekcije");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Konektovan");

                handleClient(socket);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void handleClient(Socket socket) throws Exception {
        ProcessClientsRequests processClientsRequests = new ProcessClientsRequests(socket);
        processClientsRequests.start();
    }
}
