/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.carpco.altablero.spring.web.controller;

import co.com.carpco.altablero.bo.User;
import co.com.carpco.altablero.hibernate.bll.BzUserBll;
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
 * @author Carlos
 */
@Controller
public class MainBoardController {
    
    @Autowired
    BzUserBll bzUserBO;
    
    @RequestMapping(value = { "/", "/admin/general" }, method = RequestMethod.GET)
    public ModelAndView generalPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            
            User user = bzUserBO.getUserByDocumentNumber(auth.getName());
            ModelAndView model = RoleUtils.getInstance().createModelWithUserDetails(user);            
            model.setViewName("admin/general");
            return model;
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
}
