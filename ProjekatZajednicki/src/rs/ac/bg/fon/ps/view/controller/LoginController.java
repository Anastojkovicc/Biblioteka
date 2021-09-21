/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmLogin;

/**
 *
 * @author ANA
 */
public class LoginController {

    private final FrmLogin frmLogin;

    public LoginController(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
        addActionListener();
    }

    public void openForm() {
        frmLogin.setVisible(true);
    }

    private void addActionListener() {

        frmLogin.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser(e);
            }

            private void loginUser(ActionEvent e) {
                resetForm();
                try {
                    String username = frmLogin.getTxtUsername().getText().trim();
                    String password = String.copyValueOf(frmLogin.getTxtPassword().getPassword());

                    validateForm(username, password);

                    Bibliotekar user = Controller.getInstance().login(username, password);
                    MainCordinator.getInstance().addParam(Constants.PARAM_ULOGOVANI, user);
                    JOptionPane.showMessageDialog(frmLogin, "Welcome " + user.getIme() + ", " + user.getPrezime(), "Login", JOptionPane.INFORMATION_MESSAGE);
                    frmLogin.dispose();
                    MainCordinator.getInstance().openMainForm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmLogin, ex.getMessage(), "Login error", JOptionPane.ERROR_MESSAGE);

                }
            }

            private void resetForm() {
                frmLogin.getLblUsernameError().setText("");
                frmLogin.getLblPasswordError().setText("");
            }

            private void validateForm(String username, String password) throws Exception {
                String errorMessage = "";
                if (username.isEmpty()) {
                    frmLogin.getLblUsernameError().setText("Morate uneti korisničko ime!");
                    errorMessage += "Korisničko ime ne može biti prazno\n";
                }
                if (password.isEmpty()) {
                    frmLogin.getLblPasswordError().setText("Morate uneti lozinku!");
                    errorMessage += "Lozinka ne može biti prazna\n";
                }
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
            }
        });
    }

}
