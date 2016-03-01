/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.entity.ClassBO;
import co.com.soinsoftware.altablero.entity.ClassRoomBO;
import co.com.soinsoftware.altablero.entity.UserBO;
import java.io.IOException;
import java.util.List;
import java.util.Set;
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

    private static final String CLASSROOM_CLASSES_PAGE = "/admin/cursos/clases";
    private static final String CLASSROOM_CLASSES_SAVE_PAGE = "/admin/cursos/clases/guardar";
    private static final String CLASSROOM_DEACTIVATE_PAGE = "/admin/cursos/edicion/desactivar";
    private static final String CLASSROOM_EDIT_PAGE = "/admin/cursos/edicion";
    private static final String CLASSROOM_LINK_PAGE = "/admin/cursos/asociar";
    private static final String CLASSROOM_LINK_SAVE_PAGE = "/admin/cursos/asociar/guardar";
    private static final String CLASSROOM_PAGE = "/admin/cursos";
    private static final String CLASSROOM_SAVE_PAGE = "/admin/cursos/edicion/guardar";

    private static final String CLASSROOM_CLASSES_MODEL = "admin/classroom/class";
    private static final String CLASSROOM_EDIT_MODEL = "admin/classroom/edit";
    private static final String CLASSROOM_MODEL = "admin/classroom/list";
    private static final String CLASSROOM_LINK_MODEL = "admin/classroom/link";

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
            this.addClassRoomListToModel(model, year, grade);
            this.addObjectsToClassRoomPages(model);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = CLASSROOM_EDIT_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editClassRoom(
            @RequestParam(value = CLASSROOM_ID_REQUEST_PARAM, required = false)
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
            @RequestParam(value = CLASSROOM_ID_REQUEST_PARAM, required = true)
            final Integer idClassRoom,
            @RequestParam(value = YEAR_REQUEST_PARAM, required = true)
            final int idYear,
            @RequestParam(value = GRADE_REQUEST_PARAM, required = true)
            final int idGrade,
            @RequestParam(value = TIME_REQUEST_PARAM, required = true)
            final int idTime,
            @RequestParam(value = DIRECTOR_REQUEST_PARAM, required = true)
            final int idUser,
            @RequestParam(value = CODE_REQUEST_PARAM, required = true)
            final String code,
            @RequestParam(value = NAME_REQUEST_PARAM, required = true)
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
                savedClassRoom = classRoomController.findClassRoom(this.getIdSchool(), idClassRoomForSave);
                classRoomBO.setClassSet(savedClassRoom.getClassSet());
                classRoomBO.setYearBO(savedClassRoom.getYearBO());
                classRoomBO.setStudentSet(savedClassRoom.getStudentSet());
                classRoomBO.setUserBO(savedClassRoom.getUserBO());
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
            @RequestParam(value = CLASSROOM_ID_REQUEST_PARAM, required = true)
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

    @RequestMapping(value = CLASSROOM_CLASSES_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listClasses(
            @RequestParam(value = GRADE_REQUEST_PARAM, required = false)
            final Integer idGrade,
            @RequestParam(value = CLASSROOM_REQUEST_PARAM, required = false)
            final Integer idClassRoom) {
        ModelAndView model = null;
        try {
            model = this.buildEditClassesPageModel(idClassRoom, false, false);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = CLASSROOM_CLASSES_SAVE_PAGE, method = RequestMethod.POST)
    public ModelAndView saveClasses(
            @RequestParam(value = CLASSROOM_ID_REQUEST_PARAM, required = true)
            final Integer idClassRoom,
            @RequestParam(value = OBJECT_AS_STRING_REQUEST_PARAM, required = true)
            final String objectStr) {
        ModelAndView model = null;
        try {
            final List<ClassBO> classList
                    = classController.buildClassBOListFromString(idClassRoom, objectStr);
            final Set<ClassBO> classSet = classController.saveClasses(classList);
            boolean wasSaved = true;
            boolean hasServerErrors = false;
            if (classSet == null || classSet.size() != classList.size()) {
                hasServerErrors = true;
                wasSaved = false;
            }
            model = this.buildEditClassesPageModel(idClassRoom, wasSaved, hasServerErrors);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = CLASSROOM_LINK_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView linkStudentsToClassRooms(
            @RequestParam(value = GRADE_REQUEST_PARAM, required = false)
            final Integer idGrade,
            @RequestParam(value = CLASSROOM_REQUEST_PARAM, required = false)
            final Integer idClassRoom) {
        ModelAndView model = null;
        try {
            model = this.buildLinkStudentsToClassRoomPageModel(idGrade, idClassRoom,
                    false, false);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = CLASSROOM_LINK_SAVE_PAGE, method = RequestMethod.POST)
    public ModelAndView saveLinkStudentsToClassRooms(
            @RequestParam(value = GRADE_ID_REQUEST_PARAM, required = false)
            final Integer idGrade,
            @RequestParam(value = CLASSROOM_ID_REQUEST_PARAM, required = false)
            final Integer idClassRoom,
            @RequestParam(value = OBJECT_AS_STRING_REQUEST_PARAM, required = true)
            final String objectStr) {
        ModelAndView model = null;
        try {
            final List<ClassRoomBO> classRoomList = classRoomController
                    .buildStudentsLinkedToClassRoomList(objectStr);
            final Set<ClassRoomBO> classRoomSet = classRoomController.saveClassRoomXStudent(classRoomList);
            boolean wasSaved = true;
            boolean hasServerErrors = false;
            if (classRoomSet == null || classRoomSet.size() > classRoomList.size()) {
                hasServerErrors = true;
                wasSaved = false;
            }
            model = this.buildLinkStudentsToClassRoomPageModel(idGrade, idClassRoom,
                    wasSaved, hasServerErrors);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
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
                if (classRoomBO.getStudentSet() != null) {
                    model.addObject(STUDENT_LIST_PARAMETER,
                            userController.sortUserSet(classRoomBO.getStudentSet()));
                }
            }
            this.addTeacherNotDirectorListToModel(model, currentDirector);
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

    private ModelAndView buildEditClassesPageModel(final Integer idClassRoom,
            final boolean wasSaved, final boolean hasServerErrors)
            throws IOException {
        ModelAndView model = this.buildModelAndView();
        model.setViewName(CLASSROOM_CLASSES_MODEL);
        this.addGradeListToModel(model);
        final String year = yearController.getCurrentYearString();
        this.addClassRoomListToModel(model, year, null);
        if (idClassRoom != null && idClassRoom > 0) {
            this.addClassListToModel(model, idClassRoom);
            this.addTeacherListToModel(model);
        }
        model.addObject(SAVED_PARAMETER, wasSaved);
        model.addObject(HAS_SERVER_ERRORS_PARAMETER, hasServerErrors);
        return model;
    }

    private ModelAndView buildLinkStudentsToClassRoomPageModel(final Integer idGrade,
            final Integer idClassRoom, final boolean wasSaved, final boolean hasServerErrors)
            throws IOException {
        final ModelAndView model = this.buildModelAndView();
        model.setViewName(CLASSROOM_LINK_MODEL);
        final String year = yearController.getLastYearString();
        model.addObject(YEAR_PARAMETER, year);
        this.addGradeListToModel(model);
        this.addClassRoomListToModel(model, year, null);
        this.addStudentsNotLinkedToModel(model, idGrade, idClassRoom);
        final String currentYear = yearController.getCurrentYearString();
        model.addObject(CURRENT_CLASSROOM_LIST_PARAMETER,
                classRoomController.findClassRooms(currentYear, null, this.getIdSchool()));
        model.addObject(SAVED_PARAMETER, wasSaved);
        model.addObject(HAS_SERVER_ERRORS_PARAMETER, hasServerErrors);
        return model;
    }

    private void addObjectsToClassRoomPages(final ModelAndView model)
            throws IOException {
        this.addYearListToModel(model);
        this.addGradeListToModel(model);
        this.addTimeListToModel(model);
    }
}
