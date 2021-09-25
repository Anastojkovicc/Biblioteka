/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.knjiga;

import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author ANA
 */
public class IzmeniKnjigu extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Knjiga)) {
            throw new Exception("Neispravno uneta knjiga!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Knjiga) param);
    }

}
