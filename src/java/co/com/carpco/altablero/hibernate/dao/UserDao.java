/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.bo.UserBO;
import co.com.carpco.altablero.hibernate.entities.BzUser;
import co.com.carpco.altablero.utils.Chronometer;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Rodriguez
 */
@Repository
public class UserDao extends DaoAbstract {

    public BzUser getUserByDocumentNumber(String documentNumber) {
        Chronometer chrono = new Chronometer();
        BzUser bzUser = null;
        chrono.start();

        try {
            Session session = this.getSession();
            session.beginTransaction();
            Query query = session.createQuery("from BzUser as u where u.documentNumber = :documentNumber");
            query.setParameter("documentNumber", documentNumber);
            bzUser = (BzUser) query.list().get(0);
        } catch (HibernateException ex) {
            EXCEPTION_LOGGER.error(ex.getMessage());
        } finally {
            chrono.stop();
            DAO_LOGGER.info("Class: BzUserDao, Method: getUserByDocumentNumber, Executed in: " + chrono.getTime());
        }

        return bzUser;
    }
    
    public Set<BzUser> getTeacherSet(int idSchool) {
        Chronometer chrono = new Chronometer();
        Set<BzUser> userSet = null;
        chrono.start();
        
        try {
            Session session = this.getSession();
            session.beginTransaction();
            
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("select teacher ");
            queryBuilder.append("from BzUser as teacher ");
            queryBuilder.append("inner join teacher.bzSchoolXusers as schoolXuser ");
            queryBuilder.append("inner join schoolXuser.bzSchool as school ");
            queryBuilder.append("inner join teacher.bzUserXuserTypes as userXtype ");
            queryBuilder.append("inner join userXtype.cnUserType as userType ");
            queryBuilder.append("where teacher.enabled = 1 and school.id = :idSchool and ");
            queryBuilder.append("school.enabled = 1 and userXtype.enabled = 1 and userType.code = :userCode");
            
            Query query = session.createQuery (queryBuilder.toString());
            query.setParameter("idSchool", idSchool);
            query.setParameter("userCode", "PROFE");
            userSet = new HashSet<>(query.list());
        } catch (HibernateException ex) {
            EXCEPTION_LOGGER.error(ex.getMessage());
        } finally {
            chrono.stop();
            DAO_LOGGER.info("Class: UserDao, Method: getTeacherSet, Executed in: " + chrono.getTime());
        }
        return userSet;
    }

    public Integer saveUser(UserBO userBo) {
        Chronometer chrono = new Chronometer();
        Transaction tx = null;
        Integer userId = null;
        try {
            Session session = this.getSession();
            tx = session.beginTransaction();
            BzUser bzUser = userBo.getEntity();
            userId = (Integer) session.save(bzUser);
            tx.commit();
            DAO_LOGGER.info("User saved: " + userBo.getDocumentNumber());
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            EXCEPTION_LOGGER.error(ex.getMessage());
        } finally {

            chrono.stop();
            DAO_LOGGER.info("Class: UserDao, Method: saveUser, Executed in: " + chrono.getTime());
        }
        return userId;
    }

}
