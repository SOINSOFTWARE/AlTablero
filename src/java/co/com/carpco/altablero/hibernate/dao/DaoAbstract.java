/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.dao;

import co.com.carpco.altablero.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Rodriguez
 */
public abstract class DaoAbstract {
    
    Logger EXCEPTION_LOGGER = LoggerFactory.getLogger("exception");
    
    Logger DAO_LOGGER = LoggerFactory.getLogger("dao");
    
    public Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
}