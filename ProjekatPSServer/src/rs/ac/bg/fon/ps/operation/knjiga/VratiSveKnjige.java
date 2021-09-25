/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.knjiga;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Knjiga;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author ANA
 */
public class VratiSveKnjige extends AbstractGenericOperation{

    private List<Knjiga> knjige;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        knjige = repository.getAll((Knjiga) param);
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
    
    
    
}
