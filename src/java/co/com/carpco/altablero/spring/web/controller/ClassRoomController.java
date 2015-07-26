/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.spring.web.controller;

import co.com.carpco.altablero.bll.ClassRoomBLL;
import co.com.carpco.altablero.bll.GradeBLL;
import co.com.carpco.altablero.bll.UserBLL;
import co.com.carpco.altablero.bll.YearBLL;
import co.com.carpco.altablero.entity.ClassRoomBO;
import co.com.carpco.altablero.entity.GradeBO;
import co.com.carpco.altablero.entity.UserBO;
import co.com.carpco.altablero.entity.UserTypeBO;
import co.com.carpco.altablero.entity.YearBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClassRoomController extends AbstractController {
    
    private static final String CLASSROOM_EDIT_PAGE = "/admin/cursos/edicion";
    private static final String CLASSROOM_PAGE = "/admin/cursos";
    private static final String CLASSROOM_EDIT_MODEL = "admin/classroom/edit";
    private static final String CLASSROOM_MODEL = "admin/classroom/list";
    
    private static final String CLASSROOM_PARAMETER = "classroom";
    private static final String CLASSROOM_LIST_PARAMETER = "classrooms";
    private static final String GRADE_PARAMETER = "grade";
    private static final String GRADE_LIST_PARAMETER = "grades";
    private static final String TEACHER_LIST_PARAMETER = "teachers";
    private static final String YEAR_PARAMETER = "year";
    private static final String YEAR_LIST_PARAMETER = "years";
    
    @Autowired
    private ClassRoomBLL classRoomBLL;
    
    @Autowired
    private GradeBLL gradeBLL;
    
    @Autowired
    private YearBLL yearBLL;
    
    @Autowired
    private UserBLL userBLL;
    
    @RequestMapping(value = CLASSROOM_PAGE, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView classRoomPage(@RequestParam(value = YEAR_PARAMETER, required = false) String year, 
            @RequestParam(value = GRADE_PARAMETER, required = false) Integer grade) throws IOException {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(CLASSROOM_MODEL);
            model.addObject(CLASSROOM_LIST_PARAMETER, this.getClassRoomSet(year, grade));
            this.addObjectsToClassRoomPages(model);
        } catch (IOException ex) {
            model = LoginController.buildRedirectLoginModel();
        }  
        return model;
    }
    
    @RequestMapping(value = CLASSROOM_EDIT_PAGE, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView classRoomEdit(@RequestParam(value = "classroomId", required = false) Integer idClassRoom) {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(CLASSROOM_EDIT_MODEL);
            this.addTeacherListToModel(model);
            this.addClassRoomIdToModel(model, idClassRoom);
            this.addObjectsToClassRoomPages(model);
        } catch (IOException ex) {
            model = LoginController.buildRedirectLoginModel();
        }  
        return model;
    }
    
    private void addObjectsToClassRoomPages(ModelAndView model) {
        this.addYearListToModel(model);
        this.addGradeListToModel(model);
    }
    
    private Set<ClassRoomBO> getClassRoomSet(String year, Integer grade) {
        Set<ClassRoomBO> classRoomBOSet = null;
        if (year == null || year.isEmpty()) {
            year = this.getDefaultYear();
        }
        try {
            classRoomBOSet = classRoomBLL.findClassRooms(this.getSchoolId(), year, grade, null);
        } catch (IOException ex) {
            
        }
        return classRoomBOSet;
    }
    
    private ClassRoomBO getClassRoom(Integer idClassRoom) {
        ClassRoomBO classRoomBO = null;
        try {
            Set<ClassRoomBO> classRoomBOSet = classRoomBLL.findClassRooms(this.getSchoolId(), null, null, idClassRoom);
            if (!classRoomBOSet.isEmpty()) {
                for(ClassRoomBO newClassRoomBO : classRoomBOSet) {
                    classRoomBO = newClassRoomBO;
                    break;
                }
            }
        } catch (IOException ex) {
            
        }
        return classRoomBO;
    }
    
    private String getDefaultYear() {
        Calendar date = Calendar.getInstance();
        return String.valueOf(date.get(Calendar.YEAR));
    }
    
    private void addYearListToModel(ModelAndView model) {
        model.addObject(YEAR_LIST_PARAMETER, this.getYearList());
    }
    
    private List<YearBO> getYearList() {
        List<YearBO> yearBOList = null;
        try {
            yearBOList = new ArrayList<>(yearBLL.findAll());
            Collections.sort(yearBOList);
        } catch (IOException ex) {
            
        }        
        return yearBOList;
    }
    
    private void addGradeListToModel(ModelAndView model) {
        model.addObject(GRADE_LIST_PARAMETER, this.getGradeList());
    }
    
    private List<GradeBO> getGradeList() {
        List<GradeBO> gradeBOList = null;
        try {
            gradeBOList = new ArrayList<>(gradeBLL.findAll());
            Collections.sort(gradeBOList);
        } catch (IOException ex) {
            Logger.getLogger(ClassRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return gradeBOList;
    }
    
    private void addTeacherListToModel(ModelAndView model) {
        model.addObject(TEACHER_LIST_PARAMETER, this.getTeacherList());
    }
    
    private List<UserBO> getTeacherList() {
        List<UserBO> userList = null;
        try {
            userList = new ArrayList<>( 
                    userBLL.findUsersByUserType(this.getSchoolId(), UserTypeBO.getTeacherCode()));
            Collections.sort(userList);
        } catch (IOException ex) {
            Logger.getLogger(ClassRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }
    
    private void addClassRoomIdToModel(ModelAndView model, Integer idClassRoom) {
        if(idClassRoom != null && idClassRoom > 0) {
            ClassRoomBO classRoomBO = this.getClassRoom(idClassRoom);
            model.addObject(CLASSROOM_PARAMETER, classRoomBO);
        }
    }
}
