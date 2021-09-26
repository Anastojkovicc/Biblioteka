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
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.view.constant.Constants;
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
        frmMain.jmiNoviPrimerakNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    jmiNoviPrimerakAddActionListener(evt);
                } catch (Exception ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void jmiNoviPrimerakAddActionListener(ActionEvent evt) throws Exception {
                MainCordinator.getInstance().openNoviPrimerakForm();
            }
        });

        frmMain.jmiPregledPozajmicaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent acevt) {
                jmiPregledPozajmicaAddActionListener(acevt);
            }

            private void jmiPregledPozajmicaAddActionListener(ActionEvent acevt) {
                MainCordinator.getInstance().openPregledPozajmicaForm();

            }
        });

        frmMain.jmiPregledClanovaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent act) {
                try {
                    jmiPregledClanovaAddActionListener(act);
                } catch (Exception ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void jmiPregledClanovaAddActionListener(ActionEvent e) throws Exception {
                MainCordinator.getInstance().openPregledSvihClanovaForm();
            }
        });

        frmMain.jmiClanNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiNoviClanAddActionListener(e);

            }

            private void jmiNoviClanAddActionListener(ActionEvent e) {
                MainCordinator.getInstance().openAddNewClanForm();
            }
        });

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

        frmMain.addBtnOdlogujSeActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frmMain.dispose();
                    Communication.getInstance().odjaviZaposlenog((Bibliotekar) MainCordinator.getInstance().getParam(Constants.TRENUTNO_ULOGOVANI));

                } catch (Exception ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public Frame getFrmMain() {
        return frmMain;
    }

}
