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
import rs.ac.bg.fon.ps.domain.Zanr;
import rs.ac.bg.fon.ps.view.constant.Constants;
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
                k.setZanr((Zanr) Zanr.valueOf(frmKnjiga.getCmbZanr().getSelectedItem().toString()));
                Controller c = Controller.getInstance();
                try {
                    if (!frmKnjiga.getTxtNaziv().getText().isEmpty() && !frmKnjiga.getTxtAutor().getText().isEmpty()) {
                        c.addKnjiga(k);
//            int idKnjige = c.vratiID(k);
                        JOptionPane.showMessageDialog(frmKnjiga, "Knjiga je uspesno dodata", "Uspešno dodavanje", JOptionPane.INFORMATION_MESSAGE);
                        frmKnjiga.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frmKnjiga, "Morate popuniti oba polja", "Nespešno dodavanje", JOptionPane.ERROR_MESSAGE);

                    }
                } catch (Exception ex) {
                    Logger.getLogger(FrmKnjiga.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmKnjiga, ex.getMessage());

                }
            }
        });

        frmKnjiga.addCancelBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }

            private void cancel() {
                frmKnjiga.dispose();
            }
        });

        frmKnjiga.addDeletebtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                Knjiga knjiga = makeKnjigaForm();
                try {
                    Controller.getInstance().obrisiKnjigu(knjiga);
                    JOptionPane.showMessageDialog(frmKnjiga, "Knjiga je uspesno obrisana", "Brisanje knjige", JOptionPane.INFORMATION_MESSAGE);
                    frmKnjiga.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKnjiga, "Greska u brisanju knjige" + ex.getMessage(), "Brisanje knjige", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(KnjigaController.class.getName()).log(Level.SEVERE, null, ex);
                }

                frmKnjiga.addEditBtnActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        edit();
                    }

                    private void edit() {
                        Knjiga knjiga = makeKnjigaForm();
                        try {
                            Controller.getInstance().editKnjigu(knjiga);
                            JOptionPane.showMessageDialog(frmKnjiga, "Knjiga je uspesno izmenjena", "Izmena knjige", JOptionPane.INFORMATION_MESSAGE);
                            frmKnjiga.dispose();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frmKnjiga, "Greska u izmeni knjige" + ex.getMessage(), "Izmena knjige", JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(KnjigaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                );
            }

        });

    }

    public void openForm(FormMode formMode) {
        frmKnjiga.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        prepareView(formMode);
        frmKnjiga.setVisible(true);
    }

    private void prepareView(FormMode formMode) {
        fillCbZanr();
        setUpComponents(formMode);
    }

    private void fillCbZanr() {
        frmKnjiga.getCmbZanr().removeAllItems();
        for (Zanr zanr : Zanr.values()) {
            frmKnjiga.getCmbZanr().addItem(zanr);
        }
    }

    private Knjiga makeKnjigaForm() {
        Knjiga knjiga = new Knjiga();
        knjiga.setNaziv(frmKnjiga.getTxtNaziv().getText().trim());
        knjiga.setAutor(frmKnjiga.getTxtAutor().getText().trim());
        knjiga.setZanr((Zanr) frmKnjiga.getCmbZanr().getSelectedItem());
        knjiga.setPrimerci(new ArrayList<>());
        return knjiga;
    }

    private void setUpComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmKnjiga.getBtnOdustani().setEnabled(true);
                frmKnjiga.getBtnObrisi().setEnabled(false);
                frmKnjiga.getBtnIzmeni().setEnabled(false);
                frmKnjiga.getBtnDozvoli().setEnabled(false);
                frmKnjiga.getBtnSacuvaj().setEnabled(true);

                frmKnjiga.getTxtNaziv().setEnabled(true);
                frmKnjiga.getTxtAutor().setEnabled(true);
                frmKnjiga.getCmbZanr().setEnabled(true);
                break;
            case FORM_VIEW:
                frmKnjiga.getBtnOdustani().setEnabled(true);
                frmKnjiga.getBtnObrisi().setEnabled(true);
                frmKnjiga.getBtnIzmeni().setEnabled(false);
                frmKnjiga.getBtnDozvoli().setEnabled(true);
                frmKnjiga.getBtnSacuvaj().setEnabled(false);

                frmKnjiga.getTxtNaziv().setEnabled(false);
                frmKnjiga.getTxtAutor().setEnabled(false);
                frmKnjiga.getCmbZanr().setEnabled(false);

                Knjiga knjiga = (Knjiga) MainCordinator.getInstance().getParam(Constants.PARAM_KNJIGA);
                frmKnjiga.getTxtNaziv().setText(knjiga.getNaziv());
                frmKnjiga.getTxtAutor().setText(knjiga.getAutor());
                frmKnjiga.getCmbZanr().setSelectedItem(Zanr.valueOf(knjiga.getZanr().toString()));
                break;
            case FORM_EDIT:
                frmKnjiga.getBtnOdustani().setEnabled(true);
                frmKnjiga.getBtnObrisi().setEnabled(false);
                frmKnjiga.getBtnIzmeni().setEnabled(true);
                frmKnjiga.getBtnDozvoli().setEnabled(false);
                frmKnjiga.getBtnSacuvaj().setEnabled(false);

                frmKnjiga.getTxtNaziv().setEnabled(false);
                frmKnjiga.getTxtAutor().setEnabled(true);
                frmKnjiga.getCmbZanr().setEnabled(true);
                break;
        }
    }

}
