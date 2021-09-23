/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.view.form.FrmPregledPozajmica;
import rs.ac.bg.fon.ps.view.form.component.table.PozajmicaTableModel;

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
        ArrayList<Pozajmica> listaPozajmica = Controller.getInstance().getAllPozajmice();
        PozajmicaTableModel ptm =  new PozajmicaTableModel(listaPozajmica);
        frmPregledPozajmica.getTblPozajmjica().setModel(ptm);
    }

    private void addActionListener() {
        frmPregledPozajmica.addBtnObrisiActionController(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();
            }

            private void obrisi() {
                PozajmicaTableModel ptm= (PozajmicaTableModel) frmPregledPozajmica.getTblPozajmjica().getModel();
                int red= frmPregledPozajmica.getTblPozajmjica().getSelectedRow();
                 if (red < 0) {
                    JOptionPane.showMessageDialog(frmPregledPozajmica, "Morate selektovati red", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    Pozajmica p = ptm.getPozajmica(red);
                    int option = JOptionPane.showConfirmDialog(frmPregledPozajmica, "Da li zaista želite da obrišete pozajmicu člana " + p.getClan().getIme() + " " + p.getClan().getPrezime(), "Brisanje pozajmice", JOptionPane.YES_NO_CANCEL_OPTION);
               
                    if (option == JOptionPane.YES_OPTION) {
                    Controller controller = Controller.getInstance();
                    boolean uspesno = controller.obrisiPozajmicu(p);
                    if (uspesno) {
                       ptm.obrisiPozajmicu(p);
                        JOptionPane.showMessageDialog(frmPregledPozajmica, "Uspešno obrisana pozajmica", "Brisanje", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                
            }
        });
    }
    
    
    
    
}
