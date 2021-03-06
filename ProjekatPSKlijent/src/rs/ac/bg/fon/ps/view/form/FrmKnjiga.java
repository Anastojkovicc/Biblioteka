/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.domain.Zanr;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author ANA
 */
public class FrmKnjiga extends javax.swing.JDialog {

    int idKnjige = 0;
    FormMode frmMode;

    /**
     * Creates new form FrmKnjiga
     */
    public FrmKnjiga(java.awt.Frame parent, boolean modal, FormMode formMode) {
        super(parent, modal);
        initComponents();
        this.frmMode = formMode;
        setLocationRelativeTo(null);
//        prepareView(frmMode);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNaziv = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbZanr = new javax.swing.JComboBox();
        btnIzmeni = new javax.swing.JButton();
        btnSacuvaj = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnOdustani = new javax.swing.JButton();
        btnDozvoli = new javax.swing.JButton();
        panelKnjige = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKnjige = new javax.swing.JTable();
        btnSacuvajSve = new javax.swing.JButton();
        btnObrisiTabela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dodavanje nove knjige");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Knjiga"));

        jLabel1.setText("Naziv:");

        txtAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAutorActionPerformed(evt);
            }
        });

        jLabel2.setText("Autor:");

        jLabel3.setText("??anr:");

        cmbZanr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnIzmeni.setText("Izmeni knjigu");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        btnSacuvaj.setText("Sa??uvaj knjigu");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obri??i knjigu");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnOdustani.setText("Odustani");
        btnOdustani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOdustaniActionPerformed(evt);
            }
        });

        btnDozvoli.setText("Omogu??i izmene");
        btnDozvoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDozvoliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtNaziv))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAutor)
                            .addComponent(cmbZanr, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDozvoli, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOdustani, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnIzmeni)
                        .addGap(18, 18, 18)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSacuvaj)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbZanr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIzmeni)
                    .addComponent(btnSacuvaj)
                    .addComponent(btnObrisi))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOdustani)
                    .addComponent(btnDozvoli))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblKnjige.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblKnjige);

        btnSacuvajSve.setText("Sa??uvaj sve");

        btnObrisiTabela.setText("Obri??i dodatu");

        javax.swing.GroupLayout panelKnjigeLayout = new javax.swing.GroupLayout(panelKnjige);
        panelKnjige.setLayout(panelKnjigeLayout);
        panelKnjigeLayout.setHorizontalGroup(
            panelKnjigeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKnjigeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKnjigeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelKnjigeLayout.createSequentialGroup()
                        .addComponent(btnObrisiTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSacuvajSve, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelKnjigeLayout.setVerticalGroup(
            panelKnjigeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKnjigeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelKnjigeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSacuvajSve)
                    .addComponent(btnObrisiTabela))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelKnjige, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelKnjige, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAutorActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
//        Knjiga k = new Knjiga();
//        if (txtNaziv.getText().isEmpty() || txtAutor.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Morate popuniti oba polja");
//            return;
//        }
//        k.setNaziv(txtNaziv.getText().trim());
//        k.setAutor(txtAutor.getText().trim());
//        k.setZanr(cmbZanr.getSelectedItem().toString());
//        Controller c = Controller.getInstance();
//        try {
//            c.addKnjiga(k);
////            int idKnjige = c.vratiID(k);
//            JOptionPane.showMessageDialog(this, "Knjiga je uspesno dodata");
//        } catch (Exception ex) {
//            Logger.getLogger(FrmKnjiga.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, ex.getMessage());
//
//        }
    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnOdustaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOdustaniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOdustaniActionPerformed

    private void btnDozvoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDozvoliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDozvoliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDozvoli;
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnObrisiTabela;
    private javax.swing.JButton btnOdustani;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JButton btnSacuvajSve;
    private javax.swing.JComboBox cmbZanr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelKnjige;
    private javax.swing.JTable tblKnjige;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtNaziv;
    // End of variables declaration//GEN-END:variables

//    private void prepareView(FormMode formMode) {
//        fillCBZanr();
//        setUpComponents(formMode);
//    }
//
//    private void fillCBZanr() {
//        cmbZanr.removeAllItems();
//        for (Zanr zanr : Zanr.values()) {
//            cmbZanr.addItem(zanr);
//        }
//    }
//    private void setUpComponents(FormMode formMode) {
//        switch (formMode) {
//            case FORM_ADD:
//                btnOdustani.setEnabled(false);
//                btnObrisi.setEnabled(false);
//                btnDozvoli.setEnabled(false);
//                btnIzmeni.setEnabled(false);
//                btnSacuvaj.setEnabled(true);
//                break;
//            case FORM_VIEW:
//                btnOdustani.setEnabled(false);
//                btnObrisi.setEnabled(true);
//                btnDozvoli.setEnabled(true);
//                btnIzmeni.setEnabled(false);
//                btnSacuvaj.setEnabled(false);
//                txtNaziv.setEnabled(false);
//                txtAutor.setEnabled(true);
//                break;
//        }
//    }
    public void addSacuvajBtnActionListener(ActionListener actionListener) {
        btnSacuvaj.addActionListener(actionListener);
    }

    public JComboBox getCmbZanr() {
        return cmbZanr;
    }

    public JTextField getTxtAutor() {
        return txtAutor;
    }

    public JTextField getTxtNaziv() {
        return txtNaziv;
    }

    public JButton getBtnDozvoli() {
        return btnDozvoli;
    }

    public JButton getBtnIzmeni() {
        return btnIzmeni;
    }

    public JButton getBtnObrisi() {
        return btnObrisi;
    }

    public JButton getBtnOdustani() {
        return btnOdustani;
    }

    public JButton getBtnSacuvaj() {
        return btnSacuvaj;
    }

    public JTable getTblKnjige() {
        return tblKnjige;
    }

    public void setTblKnjige(JTable tblKnjige) {
        this.tblKnjige = tblKnjige;
    }

    public JButton getBtnObrisiTabela() {
        return btnObrisiTabela;
    }

    public JButton getBtnSacuvajSve() {
        return btnSacuvajSve;
    }

    public JPanel getPanelKnjige() {
        return panelKnjige;
    }

    
    public void addCancelBtnActionListener(ActionListener actionListener) {
        btnOdustani.addActionListener(actionListener);
    }

    public void addDeletebtnActionListener(ActionListener actionListener) {
        btnObrisi.addActionListener(actionListener);
    }

    public void addEditBtnActionListener(ActionListener actionListener) {
        btnIzmeni.addActionListener(actionListener);
    }

    public void addDozvoliBtnActionListener(ActionListener actionListener) {
        btnDozvoli.addActionListener(actionListener);
    }

    public void addEnabledBtnActionListener(ActionListener actionListener) {
        btnDozvoli.addActionListener(actionListener);
    }

    public void addBtnObrisiIzTabeleActionListener(ActionListener actionListener) {
        btnObrisiTabela.addActionListener(actionListener);
    }

    public void addBtnSacuvajSveActionListener(ActionListener actionListener) {
        btnSacuvajSve.addActionListener(actionListener);
    }
}
