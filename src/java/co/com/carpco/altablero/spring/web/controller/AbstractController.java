/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.spring.web.controller;

import co.com.carpco.altablero.bll.UserBLL;
import co.com.carpco.altablero.entity.UserBO;
import co.com.carpco.altablero.utils.AuthenticationUtils;
import co.com.carpco.altablero.utils.RoleUtils;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Carlos Rodriguez
 */
public abstract class AbstractController {
    
    @Autowired
    private RoleUtils roleUtils;
    
    @Autowired
    private UserBLL userBLL;
    
    protected ModelAndView buildModelAndView() throws IOException {
        ModelAndView model;
        String documentNumber = AuthenticationUtils.getDocumentNumberFromAuthentication();
        model = this.roleUtils.createModelWithUserDetails(documentNumber);
        return model;
    }
    
    protected UserBO getLogeduser() {
        String documentNumber = AuthenticationUtils.getDocumentNumberFromAuthentication();
        UserBO user = null;
        try {
            user = this.userBLL.findUserByDocument(documentNumber);
        } catch (IOException ex) {
            
        }
        return user;
    }
    
    protected int getSchoolId() {
        UserBO user = this.getLogeduser();
        return (user != null && user.getSchool() != null) ?
                user.getSchool().getId() : 0;
    }
}
