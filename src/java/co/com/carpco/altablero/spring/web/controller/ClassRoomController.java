/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.spring.web.controller;

import co.com.carpco.altablero.bo.ClassRoomBO;
import co.com.carpco.altablero.hibernate.bll.ClassRoomBll;
import co.com.carpco.altablero.utils.RoleUtils;
import java.util.Calendar;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Carlos Rodriguez
 */
@Controller
public class ClassRoomController {
    
    @Autowired
    ClassRoomBll classRoomBll;
    
    @Autowired
    RoleUtils roleUtils;
    
    @RequestMapping(value = "/admin/cursos", method = RequestMethod.GET)
    public ModelAndView classRoomPage(@RequestParam(value = "year", required = false) String year, 
            @RequestParam(value = "grade", required = false) String grade) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            
            if (year == null || year.isEmpty()) {
                Calendar date = Calendar.getInstance();
                year = String.valueOf(date.get(Calendar.YEAR));
            }
            
            if (grade == null) {
                grade = "";
            }
            
            ModelAndView model = roleUtils.createModelWithUserDetails(auth.getName());
            Set<ClassRoomBO> classRoomBOSet = classRoomBll.getClassRoomSet(year, grade);
            model.addObject("classrooms", classRoomBOSet);
            model.setViewName("admin/classrooms");
            return model;
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
    
}
