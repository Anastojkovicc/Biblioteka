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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.view.form.FrmClan;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author ANA
 */
public class ClanController {

    private final FrmClan frmClan;

    public ClanController(FrmClan frmClan) {
        this.frmClan = frmClan;
        addActionListener();
    }

    public void openForm(FormMode formMode) {
        prepareView();
        frmClan.setVisible(true);
    }

    private void prepareView() {
        String currentDate = new SimpleDateFormat("dd.MM.yyyy.").format(new Date());
        frmClan.getTxtDatumUclanjenja().setText(currentDate);
    }

    private void addActionListener() {
        frmClan.sacuvajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    save(evt);
                } catch (ParseException ex) {
                    Logger.getLogger(ClanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void save(ActionEvent e) throws ParseException {
                Clan c = new Clan();
                if (!(frmClan.getTxtIme().getText().isEmpty()) && !(frmClan.getTxtPrezime().getText().isEmpty())
                        && !(frmClan.getTxtJMBG().getText().isEmpty()) && !(frmClan.getTxtTelefon().getText().isEmpty())
                        && !(frmClan.getTxtEmail().getText().isEmpty()) && !(frmClan.getTxtAdresa().getText().isEmpty())
                        && !(frmClan.getTxtAdresa().getText().isEmpty()) && !(frmClan.getTxtDatumUclanjenja().getText().isEmpty())) {

                    c.setIme(frmClan.getTxtIme().getText().trim());
                    c.setPrezime(frmClan.getTxtPrezime().getText().trim());
                    c.setJmbg(Integer.parseInt(frmClan.getTxtJMBG().getText().trim()));
                    c.setTelefon(Integer.parseInt(frmClan.getTxtTelefon().getText().trim()));
                    c.seteMail(frmClan.getTxtEmail().getText().trim());
                    c.setAdresa(frmClan.getTxtAdresa().getText().trim());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                    Date datum = sdf.parse(frmClan.getTxtDatumUclanjenja().getText().trim());
                    c.setDatumUclanjenja(datum);
                    c.setDatumClanarine(datum);

                    String regex = "^(.+)@(.+)$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(frmClan.getTxtEmail().getText().trim());

                    if (frmClan.getTxtJMBG().getText().length() < 5) {
                        JOptionPane.showMessageDialog(frmClan, "JMBG mora sadržati barem 13 cifara", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (frmClan.getTxtTelefon().getText().length() < 5) {
                        JOptionPane.showMessageDialog(frmClan, "Telefon mora sadržati barem 10 cifara", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!matcher.matches()) {
                        JOptionPane.showMessageDialog(frmClan, "Email nije u ispravnom formatu", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Controller controller = Controller.getInstance();
                    try {
                        controller.addClan(c);
                    } catch (Exception ex) {
                        Logger.getLogger(FrmClan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(frmClan, "Član je uspešno sacuvan i njegov broj članske karte je: " + c.getBrojClanskeKarte());
                    resetForm();
                } else {
                    JOptionPane.showMessageDialog(frmClan, "Morate popuniti sva polja!", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void resetForm() {
                frmClan.getTxtIme().setText("");
                frmClan.getTxtPrezime().setText("");
                frmClan.getTxtEmail().setText("");
                frmClan.getTxtJMBG().setText("");
                frmClan.getTxtAdresa().setText("");
                frmClan.getTxtTelefon().setText("");
            }
        });
    }

}
