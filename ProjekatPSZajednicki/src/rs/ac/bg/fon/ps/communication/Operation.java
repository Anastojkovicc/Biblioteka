/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.io.Serializable;

/**
 *
 * @author ANA
 */
public enum Operation implements Serializable{
    LOGIN,
    ADD_KNJIGA,
    GET_ALL_KNJIGA,
    GET_ALL_CLAN,
    ADD_CLAN,
    DELETE_KNJIGA,
    EDIT_KNJIGA,
    DELETE_CLAN,
    ADD_PRIMERAK,
    ADD_POZAJMICA,
    GET_ALL_POZAJMICE,
    DELETE_POZAJMICA,
    GET_ALL_PRIMERKE,
    GET_CLAN,
    GET_PRIMERAK,
    GET_CLANOVI_USLOV,
    GET_POZAJMICE_USLOV,
    RAZDUZI_SVE,
    ODJAVI_ZAPOSLENOG;
}
