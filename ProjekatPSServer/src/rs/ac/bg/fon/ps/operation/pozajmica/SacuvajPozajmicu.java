/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.pozajmica;

import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author ANA
 */
public class SacuvajPozajmicu extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
         if (param == null || !(param instanceof Pozajmica)) {
            throw new Exception("Pozajmica nije validna");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Pozajmica pozajmica = (Pozajmica) param;
        Primerak primerak = pozajmica.getPrimerak();
            primerak.setIzdat(true);
            repository.add(pozajmica);
            repository.razduzi(primerak);
    }
    
}
