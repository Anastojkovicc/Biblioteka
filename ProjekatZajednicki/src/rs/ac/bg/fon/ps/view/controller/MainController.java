/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;

/**
 *
 * @author ANA
 */
public class MainController {

    private final FrmMain frmMain;

    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }

    public void openForm() {
        frmMain.setVisible(true);
    }

    private void addActionListener() {
        frmMain.jmiPozajmicaNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jmiNovaPozajmicaAddActionListener(ae);
            }

            private void jmiNovaPozajmicaAddActionListener(ActionEvent e) {
                MainCordinator.getInstance().openFrmPozajmica();

            }
        });

        frmMain.jmiNovaKnjigaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiNovaKnjigaActionPerformed(e);
            }

            private void jmiNovaKnjigaActionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openDodajNovuKnjiguForma();
            }
        });

        frmMain.jmiPregledSvihKnjigaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jmiPregledSvihKnjigaActionPerformed(e);
                } catch (Exception ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void jmiPregledSvihKnjigaActionPerformed(ActionEvent e) throws Exception {
                MainCordinator.getInstance().openPrikazSvihKnjigaForma();
            }
        });
    }

    public Frame getFrmMain() {
        return frmMain;
    }

}
