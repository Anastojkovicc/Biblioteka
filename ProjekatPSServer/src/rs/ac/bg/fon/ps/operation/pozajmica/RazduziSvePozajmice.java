/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.pozajmica;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author ANA
 */
public class RazduziSvePozajmice extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Pozajmica> listaPoz = (List<Pozajmica>) param;
        for (Pozajmica pozajmica : listaPoz) {
            Primerak primerak = pozajmica.getPrimerak();
            primerak.setIzdat(false);

            repository.delete(pozajmica);
            repository.razduzi(primerak);

        }
    }

}
