/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ANA
 */
public interface GenericEntity extends Serializable {

    String getTableName();

    String getColumnNamesForInsert();

    String getInsertValues();

    void setId(int id);

    String getColumns();

    String tableNameForGetAll();

    List<GenericEntity> getList(ResultSet rs) throws Exception;

    String getPoljaIZmene();

    String getUslovBrisanja();

    String getUslovPretrage();

    String uslovZaNalazenje();

    GenericEntity getEntity(ResultSet rs) throws Exception;

    String getKojaKnjiga();

    String uslovZaClanarinu();

}
