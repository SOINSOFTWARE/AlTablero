/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.controller.ClassRoomController;
import co.com.soinsoftware.altablero.controller.GradeController;
import co.com.soinsoftware.altablero.controller.TimeController;
import co.com.soinsoftware.altablero.controller.UserController;
import co.com.soinsoftware.altablero.controller.YearController;
import co.com.soinsoftware.altablero.entity.ClassRoomBO;
import co.com.soinsoftware.altablero.entity.UserBO;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlos Rodriguez
 */
@Controller
public class ClassRoomRequestHandler extends AbstractRequestHandler {

    private static final String CLASSROOM_DEACTIVATE_PAGE = "/admin/cursos/edicion/desactivar";
    private static final String CLASSROOM_EDIT_PAGE = "/admin/cursos/edicion";
    private static final String CLASSROOM_PAGE = "/admin/cursos";
    private static final String CLASSROOM_SAVE_PAGE = "/admin/cursos/edicion/guardar";

    private static final String CLASSROOM_EDIT_MODEL = "admin/classroom/edit";
    private static final String CLASSROOM_MODEL = "admin/classroom/list";

    private static final String CLASSROOM_PARAMETER = "classroom";
    private static final String CLASSROOM_LIST_PARAMETER = "classrooms";
    private static final String CURRENT_YEAR_PARAMETER = "currentYear";
    private static final String DEACTIVATED_PARAMETER = "deactivated";
    private static final String GRADE_PARAMETER = "grade";
    private static final String GRADE_LIST_PARAMETER = "grades";
    private static final String SAVED_PARAMETER = "saved";
    private static final String TEACHER_LIST_PARAMETER = "teachers";
    private static final String TIME_LIST_PARAMETER = "times";
    private static final String INVALIDCODE_PARAMETER = "invalidCode";
    private static final String YEAR_PARAMETER = "year";
    private static final String YEAR_LIST_PARAMETER = "years";

    @Autowired
    private ClassRoomController classRoomController;

    @Autowired
    private GradeController gradeController;

    @Autowired
    private TimeController timeController;

    @Autowired
    private UserController userController;

    @Autowired
    private YearController yearController;

    @RequestMapping(value = CLASSROOM_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listClassRooms(
            @RequestParam(value = YEAR_PARAMETER, required = false)
            final String year,
            @RequestParam(value = GRADE_PARAMETER, required = false)
            final Integer grade)
            throws IOException {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(CLASSROOM_MODEL);
            model.addObject(CLASSROOM_LIST_PARAMETER,
                    classRoomController.findClassRooms(year, grade, this.getIdSchool()));
            this.addObjectsToClassRoomPages(model);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = CLASSROOM_EDIT_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editClassRoom(
            @RequestParam(value = "classroomId", required = false)
            final Integer idClassRoom) {
        ModelAndView model = null;
        ClassRoomBO classRoomBO = null;
        try {
            final int idSchool = this.getIdSchool();
            classRoomBO = (idClassRoom != null && idClassRoom > 0)
                    ? classRoomController.findClassRoom(idSchool, idClassRoom) : null;
            model = this.buildEditPageModel(classRoomBO, false, false, false, false);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = CLASSROOM_SAVE_PAGE, method = RequestMethod.POST)
    public ModelAndView saveClassRoom(
            @RequestParam(value = "classroomId", required = true)
            final Integer idClassRoom,
            @RequestParam(value = "year", required = true)
            final int idYear,
            @RequestParam(value = "grade", required = true)
            final int idGrade,
            @RequestParam(value = "time", required = true)
            final int idTime,
            @RequestParam(value = "director", required = true)
            final int idUser,
            @RequestParam(value = "code", required = true)
            final String code,
            @RequestParam(value = "name", required = true)
            final String name) {
        ModelAndView model = null;
        final int idClassRoomForSave = (idClassRoom == null) ? 0 : idClassRoom;
        final ClassRoomBO classRoomBO = classRoomController.buildClassRoom(
                idClassRoomForSave, code, name, this.getIdSchool(), idYear,
                idGrade, idUser, idTime);
        try {
            ClassRoomBO savedClassRoom = null;
            boolean saved = false;
            boolean invalidCode = false;
            if (classRoomController.isValidCode(this.getIdSchool(), idClassRoomForSave, code)) {
                savedClassRoom = classRoomController.saveClassRoom(classRoomBO);
                saved = true;
            } else {
                invalidCode = true;
            }
            model = this.buildEditPageModel((saved) ? savedClassRoom : classRoomBO,
                    saved, false, false, invalidCode);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = this.buildEditPageModel(classRoomBO, false, false, true, false);
        }
        return model;
    }
    
    @RequestMapping(value = CLASSROOM_DEACTIVATE_PAGE, method = RequestMethod.POST)
    public ModelAndView deactivateClassRoom(
            @RequestParam(value = "classroomId", required = true)
            final int idClassRoom) {
        ModelAndView model = null;
        final int idSchool = this.getIdSchool();
        ClassRoomBO classRoomBO = null;
        try {
            classRoomBO = classRoomController.findClassRoom(idSchool, idClassRoom);
            classRoomBO.setEnabled(false);
            final ClassRoomBO savedClassRoom = classRoomController.saveClassRoom(classRoomBO);
            model = this.buildEditPageModel(savedClassRoom, false, true, false, false);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = this.buildEditPageModel(classRoomBO, false, false, true, false);
        }
        return model;
    }

    private ModelAndView buildEditPageModel(final ClassRoomBO classRoomBO,
            final boolean wasSaved, final boolean wasDeactivated,
            final boolean hasServerErrors, final boolean invalidCode) {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(CLASSROOM_EDIT_MODEL);
            UserBO currentDirector = null;
            if (classRoomBO != null) {
                currentDirector = classRoomBO.getUserBO();
                model.addObject(CLASSROOM_PARAMETER, classRoomBO);
            }
            this.addTeacherListToModel(model, currentDirector);
            this.addObjectsToClassRoomPages(model);
            model.addObject(CURRENT_YEAR_PARAMETER, yearController.findCurrentYear());
            model.addObject(INVALIDCODE_PARAMETER, invalidCode);
            model.addObject(SAVED_PARAMETER, wasSaved);
            model.addObject(DEACTIVATED_PARAMETER, wasDeactivated);
            model.addObject(HAS_SERVER_ERRORS_PARAMETER, hasServerErrors);
        } catch (IOException ex) {
            model = LoginRequestHandler.buildRedirectLoginModel();
            LOGGER.error(ex.getMessage(), ex);
        }
        return model;
    }

    private void addObjectsToClassRoomPages(final ModelAndView model)
            throws IOException {
        this.addYearListToModel(model);
        this.addGradeListToModel(model);
        this.addTimeListToModel(model);
    }

    private void addYearListToModel(final ModelAndView model)
            throws IOException {
        model.addObject(YEAR_LIST_PARAMETER, yearController.findAll());
    }

    private void addTimeListToModel(final ModelAndView model)
            throws IOException {
        model.addObject(TIME_LIST_PARAMETER, timeController.findAll());
    }

    private void addGradeListToModel(final ModelAndView model)
            throws IOException {
        model.addObject(GRADE_LIST_PARAMETER, gradeController.findAll());
    }

    private void addTeacherListToModel(final ModelAndView model,
            final UserBO currentDirector) throws IOException {
        final int idSchool = this.getIdSchool();
        final List<UserBO> teacherList
                = userController.findTeachersNoGroupDirector(idSchool, currentDirector);
        model.addObject(TEACHER_LIST_PARAMETER, teacherList);
    }
}
