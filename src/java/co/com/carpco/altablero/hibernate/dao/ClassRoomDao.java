/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.hibernate.entities.BzClassRoom;
import co.com.carpco.altablero.utils.Chronometer;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Rodriguez
 */
@Repository
public class ClassRoomDao extends DaoAbstract {
    
    public Set<BzClassRoom> getClassRoomSet(int idSchool)
    {
        Chronometer chrono = new Chronometer();
        Set<BzClassRoom> classRoomSet = null;
        chrono.start();
        
        try {
            Session session = this.getSession();
            session.beginTransaction();
            
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("select classroom ");
            queryBuilder.append("from BzClassRoom as classroom ");
            queryBuilder.append("inner join classroom.bzSchool as school ");
            queryBuilder.append("where school.id = :idSchool and classroom.enabled = 1 and school.enabled = 1");
            
            Query query = session.createQuery (queryBuilder.toString());
            query.setParameter("idSchool", idSchool);
            classRoomSet = new HashSet<>(query.list());
        } catch (HibernateException ex) {
            EXCEPTION_LOGGER.error(ex.getMessage());
        } finally {
            chrono.stop();
            DAO_LOGGER.info("Class: ClassRoomDao, Method: getClassRoomSet, Executed in: " + chrono.getTime());
        }
        
        return classRoomSet;
    }
    
    public BzClassRoom getClassRoom(Integer index) {
        Chronometer chrono = new Chronometer();
        BzClassRoom bzClassRoom = null;
        chrono.start();
        
        try {
            Session session = this.getSession();
            session.beginTransaction();
            
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("from BzClassRoom as classroom ");
            queryBuilder.append("where classroom.id = :index");
            
            Query query = session.createQuery (queryBuilder.toString());
            query.setParameter("index", index);
            query.setCacheable(true);
            bzClassRoom = (BzClassRoom) query.list().get(0);
        } catch (HibernateException ex) {
            EXCEPTION_LOGGER.error(ex.getMessage());
        } finally {
            chrono.stop();
            DAO_LOGGER.info("Class: ClassRoomDao, Method: getClassRoom, Executed in: " + chrono.getTime());
        }
        
        return bzClassRoom;
    }    
}
