/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.hibernate.entities.BzUser;
import co.com.carpco.altablero.utils.Chronometer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Rodriguez
 */
@Repository
public class UserDao extends DaoAbstract{
    
    public BzUser getUserByDocumentNumber(String documentNumber) {
        Chronometer chrono = new Chronometer();
        BzUser bzUser = null;
        chrono.start();
        
        try {
            Session session = this.getSession();
            session.beginTransaction();
            Query query = session.createQuery ("from BzUser as u where u.documentNumber = :documentNumber");
            query.setParameter("documentNumber", documentNumber);
            query.setCacheable(true);
            bzUser = (BzUser)query.list().get(0);
        } catch (HibernateException ex) {
            EXCEPTION_LOGGER.error(ex.getMessage());
        } finally {
            chrono.stop();
            DAO_LOGGER.info("Class: BzUserDao, Method: getUserByDocumentNumber, Executed in: " + chrono.getTime());
        }
        
        return bzUser;
    }
}
