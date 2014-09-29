/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.hibernate.entities.BzUser;
import co.com.carpco.altablero.hibernate.entities.CnUserType;
import co.com.carpco.altablero.utils.Chronometer;
import java.util.Set;
import org.hibernate.Query;

/**
 *
 * @author Carlos Rodriguez
 */
public class CnUserTypeDao extends DaoAbstract implements ICnUserTypeDao{

    @Override
    public Set<CnUserType> getUserTypesLinkedToUser(int idUser) {
        Chronometer chrono = new Chronometer();
        Set<CnUserType> userTypeSet = null;
        
        chrono.start();
        
        try {
            this.getSession().beginTransaction();
            Query q = this.getSession().createQuery ("from BzUser as u where u.documentNumber = :documentNumber");
        } catch (Exception ex) {
            EXCEPTION_LOGGER.error(ex.getMessage());
        } finally {
            chrono.stop();
            DAO_LOGGER.info("Class: CnUserTypeDao, Method: getUserTypesLinkedToUser, Executed in: " + chrono.getTime());
        }
        
        return userTypeSet;
    }
}