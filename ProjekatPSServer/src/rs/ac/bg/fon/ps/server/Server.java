/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.server;

import java.net.ServerSocket;
import java.net.Socket;
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
