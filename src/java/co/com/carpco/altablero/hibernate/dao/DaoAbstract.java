/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.hibernate.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Carlos Rodriguez
 */
public abstract class DaoAbstract {
    
    private Session session;

    public Session getSession() {
        if (this.session == null) {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        return session;
    }
}
