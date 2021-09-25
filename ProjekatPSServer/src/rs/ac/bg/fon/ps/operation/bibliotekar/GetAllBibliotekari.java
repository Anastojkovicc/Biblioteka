/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.bibliotekar;

import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author ANA
 */
public class GetAllBibliotekari extends AbstractGenericOperation {

    private Bibliotekar bibliotekar;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Bibliotekar)) {
            throw new Exception("Niste uneli podatke korisnika!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        bibliotekar = (Bibliotekar) repository.getUslov(param);
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

}
