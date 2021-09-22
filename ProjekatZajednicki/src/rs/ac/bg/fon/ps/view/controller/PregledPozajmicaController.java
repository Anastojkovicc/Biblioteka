/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import rs.ac.bg.fon.ps.view.form.FrmPregledPozajmica;

/**
 *
 * @author ANA
 */
public class PregledPozajmicaController {
    private final FrmPregledPozajmica frmPregledPozajmica;

    public PregledPozajmicaController(FrmPregledPozajmica frmPregledPozajmica) {
        this.frmPregledPozajmica = frmPregledPozajmica;
    }

    public void openForm() {
        frmPregledPozajmica.setVisible(true);
    }
    
    
    
    
}
