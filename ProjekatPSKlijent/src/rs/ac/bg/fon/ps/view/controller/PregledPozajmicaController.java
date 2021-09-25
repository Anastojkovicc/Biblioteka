/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.view.form.FrmPregledPozajmica;
import rs.ac.bg.fon.ps.view.form.component.table.PozajmicaTableModel;
import rs.ac.bg.fon.ps.view.form.component.table.RazduzivanjeTableModel;

/**
 *
 * @author ANA
 */
public class PregledPozajmicaController {

    private final FrmPregledPozajmica frmPregledPozajmica;

    public PregledPozajmicaController(FrmPregledPozajmica frmPregledPozajmica) {
        this.frmPregledPozajmica = frmPregledPozajmica;
        addActionListener();
    }

    public void openForm() {
        prepareView();
        frmPregledPozajmica.setVisible(true);
    }

    private void prepareView() {
        try {
            ArrayList<Pozajmica> listaPozajmica = Communication.getInstance().getAllPozajmice();
            PozajmicaTableModel ptm = new PozajmicaTableModel(listaPozajmica);
            frmPregledPozajmica.getTblPozajmjica().setModel(ptm);
            RazduzivanjeTableModel rtm = new RazduzivanjeTableModel(new ArrayList<>());
            frmPregledPozajmica.getTblRazduzivanja().setModel(rtm);
        } catch (Exception ex) {
            Logger.getLogger(PregledPozajmicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addActionListener() {
        frmPregledPozajmica.addBtnObrisiActionController(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                razduzi();
            }

            private void razduzi() {
                PozajmicaTableModel ptm = (PozajmicaTableModel) frmPregledPozajmica.getTblPozajmjica().getModel();
                int red = frmPregledPozajmica.getTblPozajmjica().getSelectedRow();
                if (red < 0) {
                    JOptionPane.showMessageDialog(frmPregledPozajmica, "Morate selektovati red", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Pozajmica p = ptm.getPozajmica(red);
                int option = JOptionPane.showConfirmDialog(frmPregledPozajmica, "Da li zaista želite da razdužite pozajmicu člana " + p.getClan().getIme() + " " + p.getClan().getPrezime(), "Razduživanje pozajmice", JOptionPane.YES_NO_CANCEL_OPTION);

                try {
                    if (option == JOptionPane.YES_OPTION) {

                        ptm.obrisiPozajmicu(p);
                        RazduzivanjeTableModel rtm = (RazduzivanjeTableModel) frmPregledPozajmica.getTblRazduzivanja().getModel();
                        rtm.addPozajmica(p);

                    }
                } catch (Exception ex) {
                    Logger.getLogger(PregledPozajmicaController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        frmPregledPozajmica.addBtnRazduziSveActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    razduziSve();
                } catch (Exception ex) {
                    Logger.getLogger(PregledPozajmicaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void razduziSve() throws Exception {
                RazduzivanjeTableModel rtm = (RazduzivanjeTableModel) frmPregledPozajmica.getTblRazduzivanja().getModel();
                ArrayList<Pozajmica> listaRazduzivanja = (ArrayList<Pozajmica>) rtm.getListaPozajmica();
                boolean uspesno = Communication.getInstance().razduziSve(listaRazduzivanja);
                if (uspesno) {
                    JOptionPane.showMessageDialog(frmPregledPozajmica, "Uspešno razduživanje", "Razduženo", JOptionPane.INFORMATION_MESSAGE);
                    rtm.setListaPozajmica(new ArrayList<>());
                } else {
                    JOptionPane.showMessageDialog(frmPregledPozajmica, "Neuspešno razduživanje", "Greška", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        frmPregledPozajmica.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pretrazi();
                } catch (Exception ex) {
                    Logger.getLogger(PregledPozajmicaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void pretrazi() throws Exception {
                String id = frmPregledPozajmica.getTxtPretraga().getText().trim();
                if (!id.isEmpty()) {
                    int idClana = Integer.parseInt(id);
                    Pozajmica p = new Pozajmica();
                    Clan clan = new Clan();
                    clan.setBrojClanskeKarte(idClana);
                    p.setClan(clan);
                    ArrayList<Pozajmica> pozajmice = Communication.getInstance().getAllPozajmicaUslov(p);
                    PozajmicaTableModel ptm = (PozajmicaTableModel) frmPregledPozajmica.getTblPozajmjica().getModel();
                    ptm.setListaPozajmica(pozajmice);
                } else {

                    ArrayList<Pozajmica> listaPozajmica = Communication.getInstance().getAllPozajmice();
                    PozajmicaTableModel ptm = (PozajmicaTableModel) frmPregledPozajmica.getTblPozajmjica().getModel();
                    ptm.setListaPozajmica(listaPozajmica);

                }
            }
        });

    }

}
