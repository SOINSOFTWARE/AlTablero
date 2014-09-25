/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.hibernate.entities.BzUser;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos
 */
@Repository
public class BzUserDao extends DaoAbstract{
    
    public BzUser getUserByDocumentNumber(String documentNumber) {
        BzUser bzUser = null;
        
        try {
            org.hibernate.Transaction tx = this.getSession().beginTransaction();
            Query q = this.getSession().createQuery ("from BzUser as u where u.documentNumber =  '"+documentNumber+"'");
            bzUser = (BzUser)q.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return bzUser;
    }
}
