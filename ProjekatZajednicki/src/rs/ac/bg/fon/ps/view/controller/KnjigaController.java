/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmKnjiga;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author ANA
 */
public class KnjigaController {
    private final FrmKnjiga frmKnjiga;

    public KnjigaController(FrmKnjiga frmKnjiga) {
        this.frmKnjiga = frmKnjiga;
        addActionListeners();
    }

  
    private void addActionListeners() {
        frmKnjiga.addSacuvajBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                Knjiga k = new Knjiga();
        
        k.setNaziv(frmKnjiga.getTxtNaziv().getText().trim());
        k.setAutor(frmKnjiga.getTxtAutor().getText().trim());
        k.setZanr(frmKnjiga.getCmbZanr().getSelectedItem().toString());
        Controller c = Controller.getInstance();
        try {
            c.addKnjiga(k);
//            int idKnjige = c.vratiID(k);
            JOptionPane.showMessageDialog(frmKnjiga, "Knjiga je uspesno dodata");
            frmKnjiga.dispose();
            
        } catch (Exception ex) {
            Logger.getLogger(FrmKnjiga.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog( frmKnjiga, ex.getMessage());

        }
            }
        });
    }
    

    public void openForm(FormMode formMode) {
//        FrmKnjiga.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
//        prepareView(formMode);
//        FrmKnjiga.setVisible(true);
    }

    private void prepareView(FormMode formMode) {
        fillCbZanr();
        setUpComponents(formMode);
    }

    private void fillCbZanr() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setUpComponents(FormMode formMode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
