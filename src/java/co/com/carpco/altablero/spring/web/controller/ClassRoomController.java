/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.spring.web.controller;

import co.com.carpco.altablero.bll.ClassRoomBLL;
import co.com.carpco.altablero.bll.GradeBLL;
import co.com.carpco.altablero.bll.TimeBLL;
import co.com.carpco.altablero.bll.UserBLL;
import co.com.carpco.altablero.bll.YearBLL;
import co.com.carpco.altablero.entity.ClassRoomBO;
import co.com.carpco.altablero.entity.GradeBO;
import co.com.carpco.altablero.entity.TimeBO;
import co.com.carpco.altablero.entity.UserBO;
import co.com.carpco.altablero.entity.YearBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
    private static final String CLASSROOM_LIST_PAGE = "/admin/cursos";
    private static final String CLASSROOM_SAVE_PAGE = "/admin/cursos/edicion/guardar";
    
    private static final String CLASSROOM_EDIT_MODEL = "admin/classroom/edit";
    private static final String CLASSROOM_MODEL = "admin/classroom/list";
    
    private static final String CLASSROOM_PARAMETER = "classroom";
    private static final String CLASSROOM_LIST_PARAMETER = "classrooms";
    private static final String CURRENT_YEAR_PARAMETER = "currentYear";
    private static final String GRADE_PARAMETER = "grade";
    private static final String GRADE_LIST_PARAMETER = "grades";
    private static final String TEACHER_LIST_PARAMETER = "teachers";
    private static final String TIME_LIST_PARAMETER = "times";
    private static final String YEAR_PARAMETER = "year";
    private static final String YEAR_LIST_PARAMETER = "years";
    
    @Autowired
    private ClassRoomBLL classRoomBLL;
    
    @Autowired
    private GradeBLL gradeBLL;
    
    @Autowired
    private UserBLL userBLL;
    
    @Autowired
    private TimeBLL timeBLL;
    
    @Autowired
    private YearBLL yearBLL;
    
    @RequestMapping(value = CLASSROOM_LIST_PAGE, method = { RequestMethod.GET, RequestMethod.POST })
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
        ClassRoomBO classRoomBO = (idClassRoom != null && idClassRoom > 0) ? 
                this.getClassRoom(idClassRoom) : null;
        return this.buildEditPageModel(classRoomBO);
    }
    
    @RequestMapping(value = CLASSROOM_SAVE_PAGE, method = RequestMethod.POST)
    public ModelAndView saveInformation(@RequestParam(value = "classroomId", required = true) Integer idClassRoom,
            @RequestParam(value = "year", required = true) int idYear,
            @RequestParam(value = "grade", required = true) int idGrade,
            @RequestParam(value = "time", required = true) int idTime,
            @RequestParam(value = "director", required = true) int idUser,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "name", required = true) String name) {
        
        ModelAndView view = null;
        ClassRoomBO classRoom = new ClassRoomBO(0, code, name, this.getSchoolId(), 
                idYear, idGrade, idUser, idTime, new Date(), new Date(), true);
        try {
            ClassRoomBO savedClassRoomBO = classRoomBLL.saveClassRoom(classRoom);
            if (savedClassRoomBO != null) {
                view = this.buildEditPageModel(savedClassRoomBO);
            } else {
                view = this.buildEditPageModel(classRoom);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClassRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return view;
    }
    
    private ModelAndView buildEditPageModel(ClassRoomBO classRoomBO) {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(CLASSROOM_EDIT_MODEL);
            UserBO currentDirector = null;
            if(classRoomBO != null) {
                currentDirector = classRoomBO.getUserBO();
                model.addObject(CLASSROOM_PARAMETER, classRoomBO);
            }
            this.addTeacherListToModel(model, currentDirector);
            this.addObjectsToClassRoomPages(model);
            model.addObject(CURRENT_YEAR_PARAMETER, yearBLL.findCurrentYear());
        } catch (IOException ex) {
            model = LoginController.buildRedirectLoginModel();
            Logger.getLogger(ClassRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return model;
    }
    
    private void addObjectsToClassRoomPages(ModelAndView model) {
        this.addYearListToModel(model);
        this.addGradeListToModel(model);
        this.addTimeListToModel(model);
    }
    
    private Set<ClassRoomBO> getClassRoomSet(String year, Integer grade) {
        Set<ClassRoomBO> classRoomBOSet = null;
        if (year == null || year.isEmpty()) {
            year = this.getDefaultYear();
        }
        try {
            classRoomBOSet = classRoomBLL.findClassRooms(this.getSchoolId(), year, grade, null, null);
        } catch (IOException ex) {
            Logger.getLogger(ClassRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classRoomBOSet;
    }
    
    private ClassRoomBO getClassRoom(Integer idClassRoom) {
        ClassRoomBO classRoomBO = null;
        try {
            Set<ClassRoomBO> classRoomBOSet = classRoomBLL.findClassRooms(this.getSchoolId(), null, null, null, idClassRoom);
            if (classRoomBOSet != null && !classRoomBOSet.isEmpty()) {
                classRoomBO = classRoomBOSet.iterator().next();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClassRoomController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ClassRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return yearBOList;
    }
    
    private void addTimeListToModel(ModelAndView model) {
        model.addObject(TIME_LIST_PARAMETER, this.getTimeList());
    }
    
    private List<TimeBO> getTimeList() {
        List<TimeBO> timeBOList = null;
        try {
            timeBOList = new ArrayList<>(timeBLL.findAll());
            Collections.sort(timeBOList);
        } catch (IOException ex) {
            Logger.getLogger(ClassRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timeBOList;
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
    
    private void addTeacherListToModel(ModelAndView model, UserBO currentDirector) {        
        model.addObject(TEACHER_LIST_PARAMETER, this.getTeacherNoDirectorsList(currentDirector));
    }
    
    private List<UserBO> getTeacherNoDirectorsList(UserBO currentDirector) {
        List<UserBO> userList = null;
        try {
            Set<UserBO> teacherAvailableSet = userBLL.findTeacherNoDirectors(this.getSchoolId());
            userList = new ArrayList<>();
            if (teacherAvailableSet != null) {
                userList.addAll(teacherAvailableSet);
            }
            if (currentDirector != null) {
                userList.add(currentDirector);
            }
            Collections.sort(userList);
        } catch (IOException ex) {
            Logger.getLogger(ClassRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }
}