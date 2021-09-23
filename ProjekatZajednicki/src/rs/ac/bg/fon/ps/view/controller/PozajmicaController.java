/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.view.form.FrmPozajmica;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author ANA
 */
public class PozajmicaController {

    private final FrmPozajmica frmPozajmica;

    public PozajmicaController(FrmPozajmica frmPozajmica) {
        this.frmPozajmica = frmPozajmica;
        addActionListener();

    }

    public void openForm(FormMode formMode) {
        prepareView(formMode);
        frmPozajmica.setVisible(true);
    }

    private void prepareView(FormMode formMode) {
        fillDefaultValues();
    }

    private void fillDefaultValues() {
        String currentDate = new SimpleDateFormat("dd.MM.yyyy.").format(new Date());
        frmPozajmica.getTxtDatumIzdavanja().setText(currentDate);
    }

    private void addActionListener() {
        frmPozajmica.addSacuvajBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sacuvaj();
                } catch (Exception ex) {
                    Logger.getLogger(PozajmicaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void sacuvaj() throws Exception {
                if (!frmPozajmica.getTxtInventarskiBrojPrimerka().getText().isEmpty()
                        && !frmPozajmica.getTxtBrClanskeKarte().getText().isEmpty()
                        && !frmPozajmica.getTxtDatumIzdavanja().getText().isEmpty()) {

                    try {
                        int idClana = Integer.parseInt(frmPozajmica.getTxtBrClanskeKarte().getText().trim());
                        Clan clan = new Clan();
                        clan.setBrojClanskeKarte(idClana);
                        Clan clan2 = Controller.getInstance().getClan(clan);
                        if (clan2 == null) {
                            JOptionPane.showMessageDialog(frmPozajmica, "Dati član ne postoji ili nije platio članarinu", "Greška", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int brPrimerka = Integer.parseInt(frmPozajmica.getTxtInventarskiBrojPrimerka().getText().trim());
                        Primerak primerak = new Primerak();
                        primerak.setInvertarskiBroj(brPrimerka);
                        Primerak primerak2 = Controller.getInstance().getPrimerak(primerak);
                        if (primerak2 == null) {
                            JOptionPane.showMessageDialog(frmPozajmica, "Dati primerak ne postoji ili je izdat", "Greška", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                        Date datum = sdf.parse(frmPozajmica.getTxtDatumIzdavanja().getText().trim());

                        Pozajmica pozajmica = new Pozajmica(-1, datum, datum, clan2, primerak);
                        try {
                            boolean sacuvano = Controller.getInstance().addPozajmica(pozajmica);
                            if (sacuvano) {
                                JOptionPane.showMessageDialog(frmPozajmica, "Pozajmica je uspešno sačuvana", "Čuvanje pozajmice", JOptionPane.INFORMATION_MESSAGE);
                                refreshView();
                            } else {
                                JOptionPane.showMessageDialog(frmPozajmica, "Pozajmica nije sačuvana", "Greška", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(PozajmicaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(PozajmicaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(frmPozajmica, "Morate popuniti sva polja", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void refreshView() {
                fillDefaultValues();
                frmPozajmica.getTxtBrClanskeKarte().setText("");
                frmPozajmica.getTxtInventarskiBrojPrimerka().setText("");
            }
        });
    }
}
