/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.hibernate.bo;

import co.com.carpco.altablero.hibernate.dao.BzUserDao;
import co.com.carpco.altablero.hibernate.entities.BzUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos
 */
@Service
public class BzUserBO {
    
    @Autowired
    private BzUserDao bzUserDao;
    
    public BzUser getUserByDocumentNumber(String documentNumber) {
        return bzUserDao.getUserByDocumentNumber(documentNumber);
    }
}
