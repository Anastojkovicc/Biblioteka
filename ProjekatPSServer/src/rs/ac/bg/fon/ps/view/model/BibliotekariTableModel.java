/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Bibliotekar;

/**
 *
 * @author ANA
 */
public class BibliotekariTableModel extends AbstractTableModel {

    List<Bibliotekar> listaBibliotekara;
    String[] kolone = {"ID", "Ime", "Prezime"};

    public BibliotekariTableModel() {
        listaBibliotekara = new ArrayList<>();
    }

    public BibliotekariTableModel(List<Bibliotekar> listaBibliotekara) {
        this.listaBibliotekara = listaBibliotekara;
    }

    @Override
    public int getRowCount() {
        return listaBibliotekara.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Bibliotekar b = listaBibliotekara.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return b.getBibliotekarID();
            case 1:
                return b.getIme();
            case 2:
                return b.getPrezime();
            default:
                return "n/a";

        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setListaBibliotekara(List<Bibliotekar> listaBibliotekara) {
        this.listaBibliotekara = listaBibliotekara;
    }

    public List<Bibliotekar> getListaBibliotekara() {
        return listaBibliotekara;
    }

    public void dodajUlogovanog(Bibliotekar bibliotekar) {
        listaBibliotekara.add(bibliotekar);
        fireTableDataChanged();
    }

    public void izbaciUlogovanog(Bibliotekar bibliotekar) {
        listaBibliotekara.remove(bibliotekar);
        fireTableDataChanged();
    }
}
