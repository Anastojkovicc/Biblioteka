/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Bibliotekar;

/**
 *
 * @author ANA
 */
public class RepositoryUser {
    private final List<Bibliotekar> bibliotekari;

    public RepositoryUser() {
        this.bibliotekari = new ArrayList<>();
        bibliotekari.add(new Bibliotekar(11, "Admin", "Admin", "admin", "admin"));
    }
    
    
}
