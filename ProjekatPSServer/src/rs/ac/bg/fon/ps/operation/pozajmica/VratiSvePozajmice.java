/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.pozajmica;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Pozajmica;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author ANA
 */
public class VratiSvePozajmice extends AbstractGenericOperation {

    List<Pozajmica> lista;

    @Override
    protected void preconditions(Object param) throws Exception {

        if (param == null || !(param instanceof Pozajmica)) {
            throw new Exception("Pozajmica nije validna");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.lista = repository.getAll((Pozajmica) param);
    }

    public List<Pozajmica> getLista() {
        return lista;
    }

}
