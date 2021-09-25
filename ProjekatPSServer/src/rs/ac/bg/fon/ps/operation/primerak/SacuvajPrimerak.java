/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.primerak;

import rs.ac.bg.fon.ps.domain.Primerak;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author ANA
 */
public class SacuvajPrimerak extends AbstractGenericOperation {
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Primerak)) {
            throw new Exception("Primerak nije validan");
        }
    }
    
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Primerak) param);
    }
    
}
