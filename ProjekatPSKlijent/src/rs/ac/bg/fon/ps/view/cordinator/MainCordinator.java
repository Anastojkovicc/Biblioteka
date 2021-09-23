/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.cordinator;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.controller.ClanController;
import rs.ac.bg.fon.ps.view.controller.KnjigaController;
import rs.ac.bg.fon.ps.view.controller.LoginController;
import rs.ac.bg.fon.ps.view.controller.MainController;
import rs.ac.bg.fon.ps.view.controller.PozajmicaController;
import rs.ac.bg.fon.ps.view.controller.PregledClanovaController;
import rs.ac.bg.fon.ps.view.controller.PregledPozajmicaController;
import rs.ac.bg.fon.ps.view.controller.PrikazKnjigaController;
import rs.ac.bg.fon.ps.view.controller.PrimerakController;
import rs.ac.bg.fon.ps.view.form.FrmClan;
import rs.ac.bg.fon.ps.view.form.FrmKnjiga;
import rs.ac.bg.fon.ps.view.form.FrmLogin;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmPozajmica;
import rs.ac.bg.fon.ps.view.form.FrmPregledClanova;
import rs.ac.bg.fon.ps.view.form.FrmPregledKnjiga;
import rs.ac.bg.fon.ps.view.form.FrmPregledPozajmica;
import rs.ac.bg.fon.ps.view.form.FrmPrimerak;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author ANA
 */
public class MainCordinator {

    private static MainCordinator instance;
    private final MainController mainController;
    private final Map<String, Object> params;

    private MainCordinator() {
        this.mainController = new MainController(new FrmMain());
        this.params = new HashMap<>();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    public void openLoginForm() {
        LoginController loginController = new LoginController(new FrmLogin());
        loginController.openForm();
    }

    public void openMainForm() {
        mainController.openForm();
    }

    public void openDodajNovuKnjiguForma() {
        KnjigaController knjigaController = new KnjigaController(new FrmKnjiga(mainController.getFrmMain(), true, FormMode.FORM_ADD));
        knjigaController.openForm(FormMode.FORM_ADD);
    }

    public void openPrikazSvihKnjigaForma() throws Exception {
        FrmPregledKnjiga form = new FrmPregledKnjiga();
        PrikazKnjigaController prikazKnjigaKontroler = new PrikazKnjigaController(form);
        prikazKnjigaKontroler.openForm();

    }

    public void openDetaljiKnjigeForma() {
        FrmKnjiga detaljiOKnjizi = new FrmKnjiga(mainController.getFrmMain(), true, FormMode.FORM_VIEW);
        KnjigaController knjigaController = new KnjigaController(detaljiOKnjizi);
        knjigaController.openForm(FormMode.FORM_VIEW);
        addParam(Constants.PARAM_KNJIGA, detaljiOKnjizi);
    }

    public void openFrmPozajmica() {
        PozajmicaController pozajmicaController = new PozajmicaController(new FrmPozajmica());
        pozajmicaController.openForm(FormMode.FORM_ADD);

    }

    public void openAddNewClanForm() {
        ClanController clanController = new ClanController(new FrmClan());
        clanController.openForm(FormMode.FORM_ADD);
    }

    public void openPregledSvihClanovaForm() throws Exception {
        PregledClanovaController pcc = new PregledClanovaController(new FrmPregledClanova());
        pcc.openForm();

    }

    public void openPregledPozajmicaForm() {
        PregledPozajmicaController ppc = new PregledPozajmicaController(new FrmPregledPozajmica());
        ppc.openForm();
    }

    public void openNoviPrimerakForm() throws Exception {
        PrimerakController primerakController = new PrimerakController(new FrmPrimerak());
        primerakController.openForm();
    }

    public MainController getMainController() {
        return mainController;
    }

    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }

   

}
