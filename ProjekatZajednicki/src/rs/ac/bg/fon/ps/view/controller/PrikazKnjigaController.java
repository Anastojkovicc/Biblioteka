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
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
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
                    Knjiga knjiga = (Knjiga) frmPregledKnjiga.getTblKnjige().getModel();
                    MainCordinator.getInstance().addParam(Constants.PARAM_KNJIGA, knjiga); //ili sve knjige
                    MainCordinator.getInstance().openDetaljiKnjigeForma();
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
    }

    private void prepareView() {
        frmPregledKnjiga.setTitle("Detalji o knjizi");
        fillTblKnjiga();
    }

    private void fillTblKnjiga() {
        List<Knjiga> knjige;
        try {
            knjige = Controller.getInstance().getAllBooks();
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
