/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form.component.table;

import java.sql.Savepoint;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Clan;

/**
 *
 * @author ANA
 */
public class ClanTableModel extends AbstractTableModel {

    private final List<Clan> clanovi;

    String[] kolone = {"Broj clanske karte", "Ime", "Prezime", "Telefon", "Email", "Adresa", "Datum placanja clanarine"};

    public ClanTableModel(List<Clan> clanovi) {
        this.clanovi = clanovi;
    }

    @Override
    public int getRowCount() {
        if (clanovi == null) {
            return 0;
        }
        return clanovi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clan clan = clanovi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return clan.getBrojClanskeKarte();
            case 1:
                return clan.getIme();
            case 2:
                return clan.getPrezime();
            case 3:
                return clan.getTelefon();
            case 4:
                return clan.geteMail();
            case 5:
                return clan.getAdresa();
            case 6:
                return clan.getDatumClanarine();

            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0 || columnIndex == 1) {
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Clan clan = clanovi.get(rowIndex);
        switch (columnIndex) {
            case 2:
                clan.setPrezime(String.valueOf(value));
                break;
            case 3:
                clan.setTelefon(String.valueOf(value));
                break;
            case 4:
                clan.seteMail(String.valueOf(value));
                break;
            case 5:
                clan.setAdresa(String.valueOf(value));
                break;
            //    case 6: clan.setDatumClanarine(String.valueOf(value)); 
        }
    }


}