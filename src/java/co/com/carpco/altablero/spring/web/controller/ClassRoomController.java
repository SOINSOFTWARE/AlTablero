/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.spring.web.controller;

import co.com.carpco.altablero.bo.ClassRoomBO;
import co.com.carpco.altablero.bo.GradeBO;
import co.com.carpco.altablero.bo.UserBO;
import co.com.carpco.altablero.bo.YearBO;
import co.com.carpco.altablero.hibernate.bll.ClassRoomBll;
import co.com.carpco.altablero.hibernate.bll.GradeBll;
import co.com.carpco.altablero.hibernate.bll.UserBll;
import co.com.carpco.altablero.hibernate.bll.YearBll;
import co.com.carpco.altablero.utils.RoleUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
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
    UserBll userBll;
    
    @Autowired
    YearBll yearBll;
    
    @Autowired
    GradeBll gradeBll;
    
    @Autowired
    RoleUtils roleUtils;    
    
    @RequestMapping(value = "/admin/cursos", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView classRoomPage(@RequestParam(value = "year", required = false) String year, 
            @RequestParam(value = "grade", required = false) String grade) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            
            if (year == null || year.isEmpty()) {
                Calendar date = Calendar.getInstance();
                year = String.valueOf(date.get(Calendar.YEAR));
            }
            
            if (grade == null) {
                grade = "Todos";
            }
            
            ModelAndView model = roleUtils.createModelWithUserDetails(auth.getName());
            UserBO user = userBll.getUserByDocumentNumber(auth.getName());
            Set<ClassRoomBO> classRoomBOSet = classRoomBll.getClassRoomSet(user.getSchool().getId(), year, grade);
            List<GradeBO> gradeList = new ArrayList<>(gradeBll.getGradeSet());
            List<YearBO> yearList = new ArrayList<>(yearBll.getYearSet());
            Collections.sort(gradeList);
            Collections.sort(yearList);
            
            model.addObject("classrooms", classRoomBOSet);
            model.addObject("years", yearList);
            model.addObject("grades", gradeList);
            model.setViewName("admin/classroom/list");
            return model;
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
    
    @RequestMapping(value = "/admin/cursos/edicion", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView classRoomEdit(@RequestParam(value = "classroomId", required = false) Integer idClassRoom) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            ModelAndView model = roleUtils.createModelWithUserDetails(auth.getName());
            UserBO user = userBll.getUserByDocumentNumber(auth.getName());
            List<GradeBO> gradeList = new ArrayList<>(gradeBll.getGradeSet());
            List<YearBO> yearList = new ArrayList<>(yearBll.getYearSet());
            List<UserBO> teacherList = new ArrayList<>(userBll.getTeacherSet(user.getSchool().getId()));
            Collections.sort(gradeList);
            Collections.sort(yearList);
            Collections.sort(teacherList);
            
            if(idClassRoom != null) {
                ClassRoomBO classRoomBO = classRoomBll.getClassRoom(idClassRoom, user.getSchool().getId());
                model.addObject("classroom", classRoomBO);
            }            
            
            model.addObject("years", yearList);
            model.addObject("grades", gradeList);
            model.addObject("teachers", teacherList);
            model.setViewName("admin/classroom/edit");
            return model;
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
}
