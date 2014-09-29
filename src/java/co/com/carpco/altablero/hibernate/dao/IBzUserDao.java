/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.hibernate.entities.BzUser;

/**
 *
 * @author Carlos Rodriguez
 */
public interface IBzUserDao {
    
    /**
     * Gets user entity using its document number
     * @param documentNumber Document number from user
     * @return BzUser object
     */
    BzUser getUserByDocumentNumber(String documentNumber);
}
