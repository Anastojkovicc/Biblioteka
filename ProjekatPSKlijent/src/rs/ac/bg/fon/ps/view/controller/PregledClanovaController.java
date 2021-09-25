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
import rs.ac.bg.fon.ps.view.form.FrmPregledClanova;
import rs.ac.bg.fon.ps.view.form.component.table.ClanTableModel;

/**
 *
 * @author ANA
 */
public class PregledClanovaController {

    private final FrmPregledClanova frmPregledClanova;

    public PregledClanovaController(FrmPregledClanova frmPregledClanova) {
        this.frmPregledClanova = frmPregledClanova;
        addActionListener();
    }

    public void openForm() throws Exception {
        prepareView();
        frmPregledClanova.setVisible(true);
    }

    private void prepareView() throws Exception {
        fillTbl();
    }

    private void fillTbl() throws Exception {
        Communication conntroler = Communication.getInstance();
        ArrayList<Clan> clanovi = (ArrayList<Clan>) conntroler.getAllClan();
        ClanTableModel ctm = new ClanTableModel(clanovi);
        frmPregledClanova.getTblClan().setModel(ctm);
    }

    private void addActionListener() {
        frmPregledClanova.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    obrisi(e);
                } catch (Exception ex) {
                    Logger.getLogger(PregledClanovaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void obrisi(ActionEvent e) throws Exception {
                int red = frmPregledClanova.getTblClan().getSelectedRow();
                if (red < 0) {
                    JOptionPane.showMessageDialog(frmPregledClanova, "Morate selektovati red", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ClanTableModel ctm = (ClanTableModel) frmPregledClanova.getTblClan().getModel();
                Clan clan = ctm.getClanAt(red);
                int option = JOptionPane.showConfirmDialog(frmPregledClanova, "Da li zaista želite da obrišete člana " + clan.getIme() + " " + clan.getPrezime(), "Brisanje člana", JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Communication controller = Communication.getInstance();
                    boolean uspesno = controller.obrisiClana(clan);
                    if (uspesno) {
                        ctm.obrisiClana(red);
                        JOptionPane.showMessageDialog(frmPregledClanova, "Uspešno obrisan član", "Brisanje", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }
        });

        frmPregledClanova.addPretraziBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pretrazi();
                } catch (Exception ex) {
                    Logger.getLogger(PregledClanovaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void pretrazi() throws Exception {
                
                String naziv = frmPregledClanova.getTxtPretraga().getText().trim();
                if (!frmPregledClanova.getTxtPretraga().getText().isEmpty()) {
                   
                    Clan clan = new Clan();
                    clan.setIme(naziv);
                    clan.setPrezime(naziv);
                    ArrayList<Clan> clanovi = Communication.getInstance().getAllClanUslov(clan);
                    ClanTableModel ctm = (ClanTableModel) frmPregledClanova.getTblClan().getModel();
                    ctm.setClanovi(clanovi);
                } else {
                    ArrayList<Clan> clanoviSvi = (ArrayList<Clan>) Communication.getInstance().getAllClan();
                    ClanTableModel ctm = (ClanTableModel) frmPregledClanova.getTblClan().getModel();
                    ctm.setClanovi(clanoviSvi);
                }
            }
        });
    }

}
