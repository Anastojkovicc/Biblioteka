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
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.view.form.FrmPrimerak;

/**
 *
 * @author ANA
 */
public class PrimerakController {

    private final FrmPrimerak frmPrimerak;

    public PrimerakController(FrmPrimerak frmPrimerak) {
        this.frmPrimerak = frmPrimerak;
        addActionListener();
    }

    public void openForm() throws Exception {
        prepareView();
        frmPrimerak.setVisible(true);
    }

    private void prepareView() throws Exception {
        frmPrimerak.getCmbKnjiga().removeAllItems();
        Controller controller = Controller.getInstance();
        ArrayList<Knjiga> listaKnjiga = (ArrayList<Knjiga>) controller.getAllBooks();
        for (Knjiga knjiga : listaKnjiga) {
            frmPrimerak.getCmbKnjiga().addItem(knjiga);
        }
    }

    private void addActionListener() {
        frmPrimerak.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj();
            }

            private void sacuvaj() {
                Knjiga knjiga = (Knjiga) frmPrimerak.getCmbKnjiga().getSelectedItem();
                String godina = frmPrimerak.getTxtGodina().getText().trim();
                if (godina.isEmpty()) {
                    JOptionPane.showMessageDialog(frmPrimerak, "Morate uneti godinu izdanja", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Primerak primerak = new Primerak(-1, false, Integer.parseInt(godina), knjiga);
          
                boolean sacuvano;
                try {
                    sacuvano = Controller.getInstance().addPrimerak(primerak);
                    if (!sacuvano) {
                        JOptionPane.showMessageDialog(frmPrimerak, "Greška u čuvanju primerka", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(frmPrimerak, "Sačuvan primerak", "Čuvanje", JOptionPane.INFORMATION_MESSAGE);
                    frmPrimerak.getTxtGodina().setText("");
                } catch (Exception ex) {
                    Logger.getLogger(PrimerakController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

}
