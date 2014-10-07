/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.spring.web.controller;

import co.com.carpco.altablero.hibernate.bll.BzUserBll;
import co.com.carpco.altablero.hibernate.entities.BzUser;
import co.com.carpco.altablero.utils.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lina Florez
 */
@Controller
public class TeacherController {
  
  @Autowired
  BzUserBll bzUserBO;
  
  @RequestMapping(value = "/admin/profesor", method = RequestMethod.GET)
    public ModelAndView generalInformation() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            
            BzUser bzUser = bzUserBO.getUserByDocumentNumber(auth.getName());
            ModelAndView model = RoleUtils.getInstance().createModelWithUserDetails(bzUser);            
            model.setViewName("admin/user_createEdit");
            return model;
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
}