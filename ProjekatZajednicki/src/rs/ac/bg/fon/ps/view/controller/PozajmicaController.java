/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import rs.ac.bg.fon.ps.view.form.FrmPozajmica;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author ANA
 */
public class PozajmicaController {
       private final FrmPozajmica frmPozajmica;

    public PozajmicaController(FrmPozajmica frmPozajmica) {
        this.frmPozajmica = frmPozajmica;
    }

    public void openForm(FormMode formMode) {
        prepareView(formMode);
        frmPozajmica.setVisible(true);
    }
    
     private void prepareView(FormMode formMode) {
//        fillCbZanr();
//        setUpComponents(formMode);
    }
}
