/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Knjiga;

/**
 *
 * @author ANA
 */
public class RepositoryKnjiga {
    private final List<Knjiga> listaKnjiga;

    public RepositoryKnjiga() {
        this.listaKnjiga = new ArrayList<>();
    }

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }
    
    public void add(Knjiga k){
        listaKnjiga.add(k);
    }
    
}
