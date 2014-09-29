/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.hibernate.entities.CnUserType;
import java.util.Set;

/**
 *
 * @author Carlos Rodriguez
 */
public interface ICnUserTypeDao {
    
    Set<CnUserType> getUserTypesLinkedToUser(int idUser);
    
}