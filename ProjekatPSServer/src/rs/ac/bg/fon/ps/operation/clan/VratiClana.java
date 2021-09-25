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
public class VratiClana extends AbstractGenericOperation {

    private Clan clan;

    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.clan= (Clan) repository.getUslov((Clan)param);
    }

    public Clan getClan() {
        return clan;
    }
    

}
