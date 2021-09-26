/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.server.Server;
import rs.ac.bg.fon.ps.view.form.FrmKonfiguracija;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.model.BibliotekariTableModel;

/**
 *
 * @author ANA
 */
public class MainController {
     private static MainController instance;
    private final FrmMain frmMain;
    Server server;

    private MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        this.frmMain.setTitle("Server - Biblioteka");
        addActionListeners();
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController(new FrmMain());
        }
        return instance;
    }

    private void addActionListeners() {
        frmMain.addBtnStartServerActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMain.getLblStatusServera().setText("Server je pokrenut");
                frmMain.getLblStatusServera().setForeground(Color.green);
                if(server==null || !server.isAlive()){
                   server = new Server(); 
                }
                server.start();
            }
        });

        frmMain.addBtnStopServerActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server != null) {
                    Controller.getInstance().ugasiNiti();
                    server.zaustaviServer();
                    popuniTabelu();
                    frmMain.getLblStatusServera().setText("Server je stopiran");
                    frmMain.getLblStatusServera().setForeground(Color.red);
                }
            }
        });
        
        frmMain.addJmiSettingsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PodesavanjaController podesavanjaController=new PodesavanjaController(new FrmKonfiguracija(frmMain, true));
                podesavanjaController.openFrmKonfiguracija();
            }
        });
        
        
    }

    public void openForm() {
        prepareView();
        frmMain.setLocationRelativeTo(null);
        frmMain.setVisible(true);
    }

    private void prepareView() {
        frmMain.getLblStatusServera().setText("Server je stopiran");
        frmMain.getLblStatusServera().setForeground(Color.red);
        popuniTabelu();
    }

    private void popuniTabelu() {
        BibliotekariTableModel model = new BibliotekariTableModel();
        frmMain.getTblZaposleni().setModel(model);
    }

    public void dodajUlogovanog(Bibliotekar bibliotekar) {
        BibliotekariTableModel model =  (BibliotekariTableModel) frmMain.getTblZaposleni().getModel();
        model.dodajUlogovanog(bibliotekar);
    }
    
    public void izbaciOdjavljenog(Bibliotekar bibliotekar){
        BibliotekariTableModel model =  (BibliotekariTableModel) frmMain.getTblZaposleni().getModel();
        model.izbaciUlogovanog(bibliotekar);
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }
}
