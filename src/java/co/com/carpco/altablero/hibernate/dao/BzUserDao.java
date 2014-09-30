/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.hibernate.entities.BzUser;
import co.com.carpco.altablero.utils.Chronometer;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Rodriguez
 */
@Repository
public class BzUserDao extends DaoAbstract implements IBzUserDao{
    
    @Override
    public BzUser getUserByDocumentNumber(String documentNumber) {
        Chronometer chrono = new Chronometer();
        BzUser bzUser = null;
        chrono.start();
        
        try {
            this.getSession().beginTransaction();
            Query query = this.getSession().createQuery ("from BzUser as u where u.documentNumber = :documentNumber");
            query.setParameter("documentNumber", documentNumber);
            query.setCacheable(true);
            bzUser = (BzUser)query.list().get(0);
        } catch (Exception ex) {
            EXCEPTION_LOGGER.error(ex.getMessage());
        } finally {
            chrono.stop();
            DAO_LOGGER.info("Class: BzUserDao, Method: getUserByDocumentNumber, Executed in: " + chrono.getTime());
        }
        
        return bzUser;
    }
}
