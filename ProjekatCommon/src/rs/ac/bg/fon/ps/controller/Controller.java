/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import rs.ac.bg.fon.ps.domain.Bibliotekar;
import rs.ac.bg.fon.ps.repository.RepositoryUser;

/**
 *
 * @author ANA
 */
public class Controller {
    private final RepositoryUser repositoryUser;

    public Controller() {
        this.repositoryUser = new RepositoryUser();
    }
    
    public Bibliotekar login(String username, String password){
        return null;
    }
    
    
    
}
