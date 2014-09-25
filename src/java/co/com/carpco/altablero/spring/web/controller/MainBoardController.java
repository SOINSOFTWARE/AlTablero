/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.carpco.altablero.spring.web.controller;

import co.com.carpco.altablero.hibernate.bo.BzUserBO;
import co.com.carpco.altablero.hibernate.entities.BzUser;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
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
    
    @Inject
    BzUserBO bzUserBO;
    
    @RequestMapping(value = { "/", "/admin/general" }, method = RequestMethod.GET)
    public ModelAndView generalPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            
            BzUser bzUser = bzUserBO.getUserByDocumentNumber(auth.getName());
            
            ModelAndView model = new ModelAndView();
            
            model.addObject("username", bzUser.getName() + " " + bzUser.getLastName());
            model.addObject("usernameRole", "Carlos Rodriguez - Rector");
            model.addObject("avatar", "avatar5");
            
            model.addObject("canViewGradeMenu", true);
            model.addObject("canViewSubjectMenu", true);
            model.addObject("canViewTeacherMenu", true);
            model.addObject("canViewStudentMenu", true);
            
            
            
            Set<String> accessList = new HashSet();
            accessList.add("CURVE");
            accessList.add("CURCE");
            accessList.add("CURMA");
            accessList.add("CURES");
            
            accessList.add("MATVE");
            accessList.add("MATCE");
            accessList.add("MATCU");
            
            accessList.add("PROVE");
            accessList.add("PROCE");
            accessList.add("PROMA");
            accessList.add("PROCC");
            accessList.add("PROCA");
            accessList.add("PRODE");
            
            accessList.add("ESTVE");
            accessList.add("ESTCE");
            accessList.add("ESTCU");
            accessList.add("ESTCA");
            accessList.add("ESTEX");
            model.addObject("accessList", accessList);
            
            model.setViewName("admin/general");
            return model;
        } else 
        {
            return new ModelAndView("redirect:/login");
        }
    }
}
