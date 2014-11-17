/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.hibernate.entities.BzGrade;
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
public class GradeDao extends DaoAbstract {
    
    public Set<BzGrade> getGradeSet() {
        Chronometer chrono = new Chronometer();
        Set<BzGrade> gradeSet = null;
        chrono.start();
        
        try {
            Session session = this.getSession();
            session.beginTransaction();
            
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("from BzGrade as grade ");
            queryBuilder.append("where grade.enabled = 1");
            
            Query query = session.createQuery (queryBuilder.toString());
            gradeSet = new HashSet<>(query.list());
        } catch (HibernateException ex) {
            EXCEPTION_LOGGER.error(ex.getMessage());
        } finally {
            chrono.stop();
            DAO_LOGGER.info("Class: GradeDao, Method: getGradeSet, Executed in: " + chrono.getTime());
        }
        return gradeSet;
    }
}
