/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import javax.swing.JDialog;
import javax.swing.JFrame;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author ANA
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        super("Pocetna forma");
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmenuBarMain = new javax.swing.JMenuBar();
        jmenuKnjiga = new javax.swing.JMenu();
        jmiNovaKnjiga = new javax.swing.JMenuItem();
        jmiNoviPrimerak = new javax.swing.JMenuItem();
        jmiPrikazKnjiga = new javax.swing.JMenuItem();
        jmenuClanovi = new javax.swing.JMenu();
        jmiNoviClan = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jmenuKnjiga.setText("Knjiga");
        jmenuKnjiga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuKnjigaActionPerformed(evt);
            }
        });

        jmiNovaKnjiga.setText("Nova knjiga");
        jmiNovaKnjiga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovaKnjigaActionPerformed(evt);
            }
        });
        jmenuKnjiga.add(jmiNovaKnjiga);

        jmiNoviPrimerak.setText("Novi primerak");
        jmiNoviPrimerak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNoviPrimerakActionPerformed(evt);
            }
        });
        jmenuKnjiga.add(jmiNoviPrimerak);

        jmiPrikazKnjiga.setText("Prikaz svih knjiga");
        jmiPrikazKnjiga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPrikazKnjigaActionPerformed(evt);
            }
        });
        jmenuKnjiga.add(jmiPrikazKnjiga);

        jmenuBarMain.add(jmenuKnjiga);

        jmenuClanovi.setText("Članovi");
        jmenuClanovi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuClanoviActionPerformed(evt);
            }
        });

        jmiNoviClan.setText("Novi član");
        jmiNoviClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNoviClanActionPerformed(evt);
            }
        });
        jmenuClanovi.add(jmiNoviClan);

        jmenuBarMain.add(jmenuClanovi);

        setJMenuBar(jmenuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiNovaKnjigaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovaKnjigaActionPerformed
        JDialog frmKnjiga = new FrmKnjiga(this, true, FormMode.FORM_ADD);
        frmKnjiga.setVisible(true);
    }//GEN-LAST:event_jmiNovaKnjigaActionPerformed

    private void jmenuKnjigaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuKnjigaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmenuKnjigaActionPerformed

    private void jmiNoviPrimerakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNoviPrimerakActionPerformed
        JFrame frmPrimerak = new FrmPrimerak();
        frmPrimerak.setVisible(true);
    }//GEN-LAST:event_jmiNoviPrimerakActionPerformed

    private void jmenuClanoviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuClanoviActionPerformed

    }//GEN-LAST:event_jmenuClanoviActionPerformed

    private void jmiNoviClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNoviClanActionPerformed
        JFrame frmClan = new FrmClan();
        frmClan.setVisible(true);
    }//GEN-LAST:event_jmiNoviClanActionPerformed

    private void jmiPrikazKnjigaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPrikazKnjigaActionPerformed
        JFrame frm = new FrmPregledKnjiga();
        frm.setVisible(true);
    }//GEN-LAST:event_jmiPrikazKnjigaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jmenuBarMain;
    private javax.swing.JMenu jmenuClanovi;
    private javax.swing.JMenu jmenuKnjiga;
    private javax.swing.JMenuItem jmiNovaKnjiga;
    private javax.swing.JMenuItem jmiNoviClan;
    private javax.swing.JMenuItem jmiNoviPrimerak;
    private javax.swing.JMenuItem jmiPrikazKnjiga;
    // End of variables declaration//GEN-END:variables
}
