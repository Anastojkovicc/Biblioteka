/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Clan;

/**
 *
 * @author ANA
 */
public class RepositoryClan {
    private final List<Clan> clanovi;

    public RepositoryClan() {
        this.clanovi = new ArrayList<>();
    }

    public List<Clan> getClanovi() {
        return clanovi;
    }
    
    public void add(Clan c){
        clanovi.add(c);
    }
    
    
}
