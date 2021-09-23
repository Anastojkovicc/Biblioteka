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
                sacuvaj();
            }

            private void sacuvaj() {
                if (!frmPozajmica.getTxtInventarskiBrojPrimerka().getText().isEmpty()
                        && !frmPozajmica.getTxtBrClanskeKarte().getText().isEmpty()
                        && !frmPozajmica.getTxtDatumIzdavanja().getText().isEmpty()) {

                    try {
                        int idClana = Integer.parseInt(frmPozajmica.getTxtBrClanskeKarte().getText().trim());
                        //      Clan clan = Controller.getInstance().vratiClana(idClana);
                        int brPrimerka = Integer.parseInt(frmPozajmica.getTxtDatumIzdavanja().getText().trim());
                        //     Primerak primerak = Controller.getInstance().vratiPrimerak(brPrimerka);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                        Date datum = sdf.parse(frmPozajmica.getTxtDatumIzdavanja().getText().trim());
                        //Pri cuvanju u bazi dodati za sest meseci

                        //       Pozajmica pozajmica= new Pozajmica(-1, datum, datum, clan, primerak)
                        // boolean sacuvano = Controller.getInstance().addPozajmica(pozajmica);
                        //                        if(sacuvano){
                        //                        JOptionPane.showMessageDialog(frmPozajmica, "Pozajmica je uspešno sačuvana", "Čuvanje pozajmice", JOptionPane.INFORMATION_MESSAGE);
                        //                        refreshView();
                        //                        }
//                        else{
//                      JOptionPane.showMessageDialog(frmPozajmica, "Pozajmica nije sačuvana", "Greška", JOptionPane.ERROR_MESSAGE);
//                            }
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
