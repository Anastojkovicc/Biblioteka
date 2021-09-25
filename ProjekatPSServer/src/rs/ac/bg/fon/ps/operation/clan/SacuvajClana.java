/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.clan;

import rs.ac.bg.fon.ps.domain.Clan;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author ANA
 */
public class SacuvajClana extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Clan)) {
            throw new Exception("Član nije validan");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Clan) param);
    }

}
