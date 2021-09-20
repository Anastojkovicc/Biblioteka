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
import rs.ac.bg.fon.ps.view.controller.KnjigaController;
import rs.ac.bg.fon.ps.view.controller.LoginController;
import rs.ac.bg.fon.ps.view.controller.MainController;
import rs.ac.bg.fon.ps.view.controller.PrikazKnjigaController;
import rs.ac.bg.fon.ps.view.form.FrmKnjiga;
import rs.ac.bg.fon.ps.view.form.FrmLogin;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmPregledKnjiga;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author ANA
 */
public class MainCordinator {
    private static MainCordinator instance;
    private final MainController mainController;
    private final Map<String,Object> params;

    private MainCordinator() {
        this.mainController = new MainController(new FrmMain());
        this.params = new HashMap<>();
    }

    public static MainCordinator getInstance() {
        if(instance==null){
        instance= new MainCordinator();
        }
        return instance;
    }
    
    public void openLoginForm(){
        LoginController loginController = new LoginController(new FrmLogin());
        loginController.openForm();
    }
    
    public void openMainForm(){
        mainController.openForm();
    }
    
    public void openDodajNovuKnjiguForma(){
        KnjigaController knjigaController = new KnjigaController(new FrmKnjiga(mainController.getFrmMain(), true));
        knjigaController.openForm(FormMode.FORM_ADD);
    }
    
    public void openPrikazSvihKnjigaForma(){
        FrmPregledKnjiga form = new FrmPregledKnjiga();
        PrikazKnjigaController prikazKnjigaKontroler = new PrikazKnjigaController(form);
        prikazKnjigaKontroler.openForm();
        
        
    }

    public void openDetaljiKnjigeForma() {
        FrmKnjiga detaljiOKnjizi = new FrmKnjiga(mainController.getFrmMain(), true);
        KnjigaController knjigaController =  new KnjigaController(detaljiOKnjizi);
        knjigaController.openForm(FormMode.FORM_VIEW);
        
    }

    public MainController getMainController() {
        return mainController;
    }
    
    public void addParam(String name, Object key){
        params.put(name, key);
    }
    
    public Object getParam(String name){
        return params.get(name);
    }
    
    
    
    
}
