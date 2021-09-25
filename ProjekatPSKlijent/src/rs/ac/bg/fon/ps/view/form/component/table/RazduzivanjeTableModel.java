/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Pozajmica;

/**
 *
 * @author ANA
 */
public class RazduzivanjeTableModel extends AbstractTableModel {

    List<Pozajmica> listaPozajmica;
    String[] kolone = {"Broj članske karte", "Inventarski broj primerka", "Rok za vraćanje"};

    /**
     *
     * @param list
     */
    public RazduzivanjeTableModel(List<Pozajmica> listaPozajmica) {
        this.listaPozajmica = listaPozajmica;
    }

    public void setListaPozajmica(List<Pozajmica> listaPozajmica) {
        this.listaPozajmica = listaPozajmica;
        fireTableDataChanged();
    }

    public void addPozajmica(Pozajmica p) {
        listaPozajmica.add(p);
        fireTableDataChanged();
    }

    public List<Pozajmica> getListaPozajmica() {
        return listaPozajmica;
    }

    @Override
    public int getRowCount() {
        return listaPozajmica.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pozajmica p = listaPozajmica.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getClan().getBrojClanskeKarte();
            case 1:
                return p.getPrimerak().getInvertarskiBroj();
            case 2:
                return p.getDatumVracanja();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public Pozajmica getPozajmica(int red) {
        return listaPozajmica.get(red);
    }

    public void obrisiPozajmicu(Pozajmica p) {
        listaPozajmica.remove(p);
        fireTableDataChanged();
    }

}
