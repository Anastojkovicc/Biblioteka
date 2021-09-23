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
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.domain.Zanr;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmKnjiga;
import rs.ac.bg.fon.ps.view.form.FrmPregledKnjiga;
import rs.ac.bg.fon.ps.view.form.component.table.KnjigaTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author ANA
 */
public class KnjigaController {

    private final FrmKnjiga frmKnjiga;
    private int idKnjige;

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
                k.setKnjigaID(0);
                k.setNaziv(frmKnjiga.getTxtNaziv().getText().trim());
                k.setAutor(frmKnjiga.getTxtAutor().getText().trim());
                k.setZanr((Zanr) Zanr.valueOf(frmKnjiga.getCmbZanr().getSelectedItem().toString()));
                KnjigaTableModel ktblm = (KnjigaTableModel) frmKnjiga.getTblKnjige().getModel();
                if (!frmKnjiga.getTxtNaziv().getText().isEmpty() && !frmKnjiga.getTxtAutor().getText().isEmpty()) {
                    ktblm.addKnjiga(k);
                    frmKnjiga.getTxtNaziv().setText("");
                    frmKnjiga.getTxtAutor().setText("");
                } else {
                    JOptionPane.showMessageDialog(frmKnjiga, "Morate popuniti oba polja", "Dodavanje knjige", JOptionPane.ERROR_MESSAGE);
                    return;
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
                    int option = JOptionPane.showConfirmDialog(frmKnjiga, "Da li zaista želite da obrišete knjigu " + knjiga.getNaziv() + " od autora " + knjiga.getAutor(), "Brisanje knjige", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        boolean uspesno = Communication.getInstance().obrisiKnjigu(knjiga);
                        if (uspesno) {
                            JOptionPane.showMessageDialog(frmKnjiga, "Knjiga je uspešno obrisana", "Brisanje knjige", JOptionPane.INFORMATION_MESSAGE);
                            frmKnjiga.dispose();
                        }
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKnjiga, "Greska u brisanju knjige" + ex.getMessage(), "Brisanje knjige", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(KnjigaController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        frmKnjiga.addEditBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }

            private void edit() {
                Knjiga knjiga3 = makeKnjigaForm();
                try {
                    Communication.getInstance().editKnjigu(knjiga3);
                    JOptionPane.showMessageDialog(frmKnjiga, "Knjiga je uspešno izmenjena", "Izmena knjige", JOptionPane.INFORMATION_MESSAGE);
                    frmKnjiga.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKnjiga, "Greška u izmeni knjige" + ex.getMessage(), "Izmena knjige", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(KnjigaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frmKnjiga.addEnabledBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dozvoli();
            }

            private void dozvoli() {
                setUpComponents(FormMode.FORM_EDIT);
            }
        });

        frmKnjiga.addBtnObrisiIzTabeleActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiKnjigu();
            }

            private void obrisiKnjigu() {
                KnjigaTableModel ktm = (KnjigaTableModel) frmKnjiga.getTblKnjige().getModel();
                int red = frmKnjiga.getTblKnjige().getSelectedRow();
                if (red < 0) {
                    JOptionPane.showMessageDialog(frmKnjiga, "Morate selektovati red", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Knjiga knjiga = ktm.getKnjiga(red);
                int option = JOptionPane.showConfirmDialog(frmKnjiga, "Da li zaista želite da obrišete knjigu " + knjiga.getNaziv() + " od autora " + knjiga.getAutor(), "Brisanje knjige", JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == JOptionPane.YES_OPTION) {

                    ktm.obrisiKnjigu(knjiga);
                    JOptionPane.showMessageDialog(frmKnjiga, "Uspešno obrisana knjiga", "Brisanje", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        frmKnjiga.addBtnSacuvajSveActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajSve();
            }

            private void sacuvajSve() {

                Communication c = Communication.getInstance();
                KnjigaTableModel ktm = (KnjigaTableModel) frmKnjiga.getTblKnjige().getModel();
                ArrayList<Knjiga> liste = (ArrayList<Knjiga>) ktm.getKnjige();
                try {
                    for (Knjiga knjiga : liste) {
                        c.addKnjiga(knjiga);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKnjiga, "Greška pri unosu liste u bazu", "Greška", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(KnjigaController.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
                JOptionPane.showMessageDialog(frmKnjiga, "Knjige su uspešno dodate", "Uspešno dodavanje", JOptionPane.INFORMATION_MESSAGE);
                frmKnjiga.getTblKnjige().setModel(new KnjigaTableModel(new ArrayList<>()));

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
        setTable();
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
        knjiga.setKnjigaID(idKnjige);
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
                frmKnjiga.getPanelKnjige().setVisible(true);

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
                frmKnjiga.getPanelKnjige().setVisible(false);

                frmKnjiga.getTxtNaziv().setEnabled(false);
                frmKnjiga.getTxtAutor().setEnabled(false);
                frmKnjiga.getCmbZanr().setEnabled(false);

                Knjiga knjiga = (Knjiga) MainCordinator.getInstance().getParam(Constants.PARAM_KNJIGA);
                idKnjige = knjiga.getKnjigaID();
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
                frmKnjiga.getPanelKnjige().setVisible(false);

                frmKnjiga.getTxtNaziv().setEnabled(false);
                frmKnjiga.getTxtAutor().setEnabled(true);
                frmKnjiga.getCmbZanr().setEnabled(true);
                break;
        }
    }

    private void setTable() {
        KnjigaTableModel ktm = new KnjigaTableModel(new ArrayList<>());
        frmKnjiga.getTblKnjige().setModel(ktm);
    }

}
