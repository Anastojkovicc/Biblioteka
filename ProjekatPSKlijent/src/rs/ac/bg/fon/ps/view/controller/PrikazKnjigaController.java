/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmPregledKnjiga;
import rs.ac.bg.fon.ps.view.form.component.table.KnjigaTableModel;

/**
 *
 * @author ANA
 */
public class PrikazKnjigaController {

    private final FrmPregledKnjiga frmPregledKnjiga;

    public PrikazKnjigaController(FrmPregledKnjiga frmPregledKnjiga) {
        this.frmPregledKnjiga = frmPregledKnjiga;
        addActionListener();
    }

    public void openForm() {
        frmPregledKnjiga.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        prepareView();
        frmPregledKnjiga.setVisible(true);
    }

    private void addActionListener() {
        frmPregledKnjiga.getBtnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPregledKnjiga.getTblKnjige().getSelectedRow();
                if (row >= 0) {

                    KnjigaTableModel ktm = (KnjigaTableModel) frmPregledKnjiga.getTblKnjige().getModel();
                    Knjiga knjiga = ktm.getKnjiga(row);
                    MainCordinator.getInstance().addParam(Constants.PARAM_KNJIGA, knjiga); 
                    MainCordinator.getInstance().openDetaljiKnjigeForma();
                    ktm.fireTableDataChanged();
                } else {
                    JOptionPane.showMessageDialog(frmPregledKnjiga, "Morate selektovati red", "Detalji o knjizi", JOptionPane.ERROR);
                }
            }
        });

        frmPregledKnjiga.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTblKnjiga();
            }

        });

        frmPregledKnjiga.addBtnDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    obrisi(e);
                } catch (Exception ex) {
                    Logger.getLogger(PrikazKnjigaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void obrisi(ActionEvent e) throws Exception {
                int red = frmPregledKnjiga.getTblKnjige().getSelectedRow();
                if (red < 0) {
                    JOptionPane.showMessageDialog(frmPregledKnjiga, "Morate selektovati red", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                KnjigaTableModel ktm = (KnjigaTableModel) frmPregledKnjiga.getTblKnjige().getModel();
                Knjiga knjiga = ktm.getKnjiga(red);
                int option = JOptionPane.showConfirmDialog(frmPregledKnjiga, "Da li zaista želite da obrišete knjigu " + knjiga.getNaziv() + " od autora " + knjiga.getAutor(), "Brisanje knjige", JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Communication controller = Communication.getInstance();
                    boolean uspesno = controller.obrisiKnjigu(knjiga);
                    if (uspesno) {
                        ktm.obrisiKnjigu(knjiga);
                        JOptionPane.showMessageDialog(frmPregledKnjiga, "Uspešno obrisana knjiga", "Brisanje", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    private void prepareView() {
        frmPregledKnjiga.setTitle("Detalji o knjizi");
        fillTblKnjiga();
    }

    private void fillTblKnjiga() {
        List<Knjiga> knjige;
        try {
            knjige = Communication.getInstance().getAllBooks();
            KnjigaTableModel ktm = new KnjigaTableModel(knjige);
            frmPregledKnjiga.getTblKnjige().setModel(ktm);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(frmPregledKnjiga, e.getMessage());
        }
    }

    public void refresh() {
        fillTblKnjiga();
    }

}
