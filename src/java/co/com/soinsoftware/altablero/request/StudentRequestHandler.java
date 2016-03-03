/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.entity.ClassRoomBO;
import static co.com.soinsoftware.altablero.request.AbstractRequestHandler.CLASSROOM_PARAMETER;
import static co.com.soinsoftware.altablero.request.AbstractRequestHandler.LOGGER;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlos Rodriguez
 * @since 03/03/2016
 * @version 1.0
 */
@Controller
public class StudentRequestHandler extends AbstractRequestHandler {
    
    private static final String STUDENT_PAGE = "/admin/estudiantes";
    
    private static final String STUDENT_MODEL = "admin/student/list";
    
    @RequestMapping(value = STUDENT_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView list(
            @RequestParam(value = GRADE_REQUEST_PARAM, required = false)
            final Integer idGrade,
            @RequestParam(value = CLASSROOM_REQUEST_PARAM, required = false)
            final Integer idClassRoom) {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(STUDENT_MODEL);
            final String currentYear = this.yearController.getCurrentYearString();
            this.addClassRoomListToModel(model, currentYear, 0);
            this.addGradeListToModel(model);
            final int idSchool = this.getIdSchool();
            final ClassRoomBO classRoom = (idClassRoom != null && idClassRoom > 0)
                    ? classRoomController.findClassRoom(idSchool, idClassRoom) : null;
            if (classRoom != null && classRoom.getStudentSet() != null) {
                model.addObject(STUDENT_LIST_PARAMETER, 
                        userController.sortUserSet(classRoom.getStudentSet()));
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }    
}
