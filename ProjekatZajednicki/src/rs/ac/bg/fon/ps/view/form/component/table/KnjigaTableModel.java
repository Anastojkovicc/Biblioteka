/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Knjiga;

/**
 *
 * @author ANA
 */
public class KnjigaTableModel extends AbstractTableModel {

    private final List<Knjiga> knjige;

    public KnjigaTableModel(List<Knjiga> knjige) {
        this.knjige = knjige;
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
    
    
    

    @Override
    public int getRowCount() {
        if (knjige == null) {
            return 0;
        }
        return knjige.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjiga k = knjige.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getKnjigaID();
            case 1:
                return k.getNaziv();
            case 2:
                return k.getAutor();
            case 3:
                return k.getZanr();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Naziv";
            case 2:
                return "Autor";
            case 3:
                return "Žanr";
            default:
                return "n/a";
        }
    }

    public void addKnjiga(Knjiga knjiga) {
        knjige.add(knjiga);
        fireTableRowsInserted(knjige.size() - 1, knjige.size() - 1);
    }

    public Knjiga getKnjiga(int red) {
        return knjige.get(red);
    }

    public void obrisiKnjigu(Knjiga knjiga) {
        knjige.remove(knjiga);
        fireTableDataChanged();
    }

}
