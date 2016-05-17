/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.entity.ClassBO;
import co.com.soinsoftware.altablero.entity.ClassRoomBO;
import co.com.soinsoftware.altablero.entity.NoteDefinitionBO;
import co.com.soinsoftware.altablero.entity.NoteValueBO;
import co.com.soinsoftware.altablero.entity.PeriodBO;
import co.com.soinsoftware.altablero.entity.SchoolBO;
import co.com.soinsoftware.altablero.entity.UserBO;
import co.com.soinsoftware.altablero.entity.UserTypeBO;
import static co.com.soinsoftware.altablero.request.AbstractRequestHandler.LOGGER;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 23/02/2016
 */
@Controller
public class TeacherRequestHandler extends AbstractRequestHandler {

    private static final String TEACHER_ACTIVITY_PAGE = "/admin/profesores/actividades";
    private static final String TEACHER_ACTIVITY_SAVE_PAGE = "/admin/profesores/actividades/guardar";
    private static final String TEACHER_DEACTIVATE_PAGE = "/admin/profesores/edicion/desactivar";
    private static final String TEACHER_EDIT_PAGE = "/admin/profesores/edicion";
    private static final String TEACHER_EVALUATE_PAGE = "/admin/profesores/calificar";
    private static final String TEACHER_EVALUATE_SAVE_PAGE = "/admin/profesores/calificar/guardar";
    private static final String TEACHER_PAGE = "/admin/profesores";
    private static final String TEACHER_REPORT_PAGE = "/admin/profesores/boletines";
    private static final String TEACHER_REPORT_GENERATE_PAGE = "/admin/profesores/boletines/generar";
    private static final String TEACHER_SAVE_PAGE = "/admin/profesores/edicion/guardar";

    private static final String TEACHER_ACTIVITY_MODEL = "admin/teacher/activity";
    private static final String TEACHER_EDIT_MODEL = "admin/teacher/edit";
    private static final String TEACHER_EVALUATE_MODEL = "admin/teacher/evaluate";
    private static final String TEACHER_MODEL = "admin/teacher/list";
    private static final String TEACHER_REPORT_MODEL = "admin/teacher/report";

    @RequestMapping(value = TEACHER_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView list() {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(TEACHER_MODEL);
            this.addTeacherListToModel(model);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = TEACHER_EDIT_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView edit(@RequestParam(value = USER_ID_REQUEST_PARAM, required = false)
            final Integer idUser) {
        ModelAndView model = null;
        try {
            final UserBO user = this.findUserByIdentifier(idUser);
            model = this.buildEditPageModel(user, false, false, false, false);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = this.buildEditPageModel(null, false, false, true, false);
        }
        return model;
    }

    @RequestMapping(value = TEACHER_SAVE_PAGE, method = RequestMethod.POST)
    public ModelAndView save(
            @RequestParam(value = USER_ID_REQUEST_PARAM, required = true)
            final Integer idUser,
            @RequestParam(value = DOCUMENT_TYPE_REQUEST_PARAM, required = true)
            final String docType,
            @RequestParam(value = DOCUMENT_NUMBER_REQUEST_PARAM, required = true)
            final String docNumber,
            @RequestParam(value = NAME_REQUEST_PARAM, required = true)
            final String name,
            @RequestParam(value = LAST_NAME_REQUEST_PARAM, required = true)
            final String lastName,
            @RequestParam(value = BORN_DATE_REQUEST_PARAM, required = true)
            final String bornDate,
            @RequestParam(value = ADDRESS_REQUEST_PARAM, required = true)
            final String address,
            @RequestParam(value = PHONE1_REQUEST_PARAM, required = true)
            final String phone1,
            @RequestParam(value = PHONE2_REQUEST_PARAM, required = true)
            final String phone2,
            @RequestParam(value = GENDER_REQUEST_PARAM, required = true)
            final String gender,
            @RequestParam(value = FILE_UPLOAD_REQUEST_PARAM, required = true)
            final MultipartFile file,
            @RequestParam(value = COORDINATOR_REQUEST_PARAM, required = false)
            final String coordinator) {
        ModelAndView model = null;
        try {
            final SchoolBO school = this.schoolController.findByIdentifier(this.getIdSchool());
            final Set<UserTypeBO> userTypeSet = new HashSet<>();
            if (coordinator != null) {
                userTypeSet.add(this.userTypeController.findBy(UserTypeBO.getCoordinatorCode()));
            }
            userTypeSet.add(this.userTypeController.findBy(UserTypeBO.getTeacherCode()));
            final boolean isValidDocNumber = userController.isValidDocumentNumber(
                    idUser, docNumber, this.getIdSchool());
            UserBO user = this.userController.buildUserBO(idUser, docType, docNumber, name,
                    lastName, bornDate, address, phone1, phone2, gender, file, school, userTypeSet);
            boolean saved = false;
            boolean hasServerErrors = false;
            if (isValidDocNumber) {
                final UserBO savedUser = this.userController.save(user);
                if (savedUser != null) {
                    saved = true;
                    user = savedUser;
                } else {
                    hasServerErrors = true;
                }
            }
            model = this.buildEditPageModel(user, saved, false, hasServerErrors, !isValidDocNumber);
        } catch (IOException | ParseException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = this.buildEditPageModel(null, false, false, true, false);
        }
        return model;
    }

    @RequestMapping(value = TEACHER_DEACTIVATE_PAGE, method = RequestMethod.POST)
    public ModelAndView deactivate(
            @RequestParam(value = USER_ID_REQUEST_PARAM, required = true)
            final Integer idUser) {
        ModelAndView model = null;
        try {
            UserBO user = this.findUserToBeDeactivated(idUser);
            final UserBO savedUser = this.userController.save(user);
            boolean saved = false;
            boolean hasServerErrors = false;
            if (savedUser != null) {
                saved = true;
                user = savedUser;
            } else {
                hasServerErrors = true;
            }
            model = this.buildEditPageModel(user, false, saved, hasServerErrors, false);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = this.buildEditPageModel(null, false, false, true, false);
        }
        return model;
    }

    @RequestMapping(value = TEACHER_ACTIVITY_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView activityList(
            @RequestParam(value = CLASSROOM_REQUEST_PARAM, required = false)
            final Integer idClassRoom,
            @RequestParam(value = CLASS_REQUEST_PARAM, required = false)
            final Integer idClass,
            @RequestParam(value = PERIOD_REQUEST_PARAM, required = false)
            final Integer idPeriod) {
        ModelAndView model = null;
        try {
            model = this.buildActivityPageModel(TEACHER_ACTIVITY_MODEL, idClass,
                    idPeriod, false, false);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = TEACHER_ACTIVITY_SAVE_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView saveActivityList(
            @RequestParam(value = CLASSROOM_ID_REQUEST_PARAM, required = true)
            final Integer idClassRoom,
            @RequestParam(value = CLASS_ID_REQUEST_PARAM, required = true)
            final Integer idClass,
            @RequestParam(value = PERIOD_ID_REQUEST_PARAM, required = true)
            final Integer idPeriod,
            @RequestParam(value = OBJECT_AS_STRING_REQUEST_PARAM, required = true)
            final String objectStr) {
        ModelAndView model = null;
        try {
            final List<NoteDefinitionBO> noteDefList = this.noteDefController
                    .buildNoteDefinitionListFromString(idClass, idPeriod, objectStr);
            final ClassBO classBO = this.classController.saveNoteDefinitionByClass(noteDefList);
            boolean saved = false;
            boolean hasServerErrors = false;
            if (classBO != null && classBO.getNoteDefinitionSet() != null
                    && !classBO.getNoteDefinitionSet().isEmpty()) {
                saved = true;
            } else {
                hasServerErrors = true;
            }
            model = this.buildActivityPageModel(TEACHER_ACTIVITY_MODEL, idClass,
                    idPeriod, saved, hasServerErrors);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = TEACHER_EVALUATE_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView evaluate(
            @RequestParam(value = CLASSROOM_REQUEST_PARAM, required = false)
            final Integer idClassRoom,
            @RequestParam(value = CLASS_REQUEST_PARAM, required = false)
            final Integer idClass,
            @RequestParam(value = PERIOD_REQUEST_PARAM, required = false)
            final Integer idPeriod) {
        ModelAndView model = null;
        try {
            model = this.buildActivityPageModel(TEACHER_EVALUATE_MODEL, idClass,
                    idPeriod, false, false);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = TEACHER_EVALUATE_SAVE_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView saveEvaluation(
            @RequestParam(value = CLASSROOM_ID_REQUEST_PARAM, required = false)
            final Integer idClassRoom,
            @RequestParam(value = CLASS_ID_REQUEST_PARAM, required = false)
            final Integer idClass,
            @RequestParam(value = PERIOD_ID_REQUEST_PARAM, required = false)
            final Integer idPeriod,
            @RequestParam(value = OBJECT_AS_STRING_REQUEST_PARAM, required = true)
            final String objectStr) {
        ModelAndView model = null;
        try {
            final List<NoteValueBO> noteValueList = this.noteValueController
                    .buildNoteValueListFromString(objectStr);
            final ClassBO classBO = this.classController.saveNoteValue(noteValueList);
            boolean saved = false;
            boolean hasServerErrors = false;
            if (classBO != null && classBO.getNoteDefinitionSet() != null
                    && !classBO.getNoteDefinitionSet().isEmpty()) {
                saved = true;
            } else {
                hasServerErrors = true;
            }
            model = this.buildActivityPageModel(TEACHER_EVALUATE_MODEL, idClass,
                    idPeriod, saved, hasServerErrors);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = TEACHER_REPORT_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView report() {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(TEACHER_REPORT_MODEL);
            final List<PeriodBO> periodList = this.findPeriodListBySchool();
            final UserBO teacher = this.getLogeduser();
            final ClassRoomBO classRoom = this.getGroupDirectorClassRoom(teacher);
            model.addObject(PERIOD_LIST_PARAMETER, periodList);
            model.addObject(CLASSROOM_PARAMETER, classRoom);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }
    
    @RequestMapping(value = TEACHER_REPORT_GENERATE_PAGE, method = RequestMethod.POST)
    public void generateReports(
            @RequestParam(value = CLASSROOM_ID_REQUEST_PARAM, required = false)
            final Integer idClassRoom,
            @RequestParam(value = PERIOD_REQUEST_PARAM, required = false)
            final Integer idPeriod,
            HttpServletResponse response) {
        try {
            InputStream is = this.generateReports(this.getIdSchool(), idClassRoom, idPeriod);
            response.setHeader("Content-Disposition", "attachment; filename=boletines.zip");
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    private ModelAndView buildEditPageModel(final UserBO user, final boolean wasSaved,
            final boolean wasDeactivated, final boolean hasServerErrors,
            final boolean invalidDocNumber) {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(TEACHER_EDIT_MODEL);
            if (user != null) {
                if (user.getPhoto() != null && !user.getPhoto().equals("")) {
                    user.setPhoto(this.userController.getHttpPath(user, this.getIdSchool()));
                }
                if (user.getId() != null && user.getId() > 0) {
                    this.addClassListToModel(model, 0, user.getId(), false);
                    model.addObject(CLASSROOM_PARAMETER, this.getGroupDirectorClassRoom(user));
                }
            }
            model.addObject(USER_PARAMETER, user);
            model.addObject(SAVED_PARAMETER, wasSaved);
            model.addObject(DEACTIVATED_PARAMETER, wasDeactivated);
            model.addObject(HAS_SERVER_ERRORS_PARAMETER, hasServerErrors);
            model.addObject(INVALIDCODE_PARAMETER, invalidDocNumber);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    private ModelAndView buildActivityPageModel(final String viewName,
            final Integer idClass, final Integer idPeriod, final boolean wasSaved,
            final boolean hasServerErrors) throws IOException {
        ModelAndView model = this.buildModelAndView();
        model.setViewName(viewName);
        final List<ClassBO> classList = classController.findClasses(
                this.getIdSchool(), 0, this.getLogeduser().getId(), false);
        final List<ClassRoomBO> classRoomList = this.
                buildClassRoomListFromClassList(classList);
        final List<PeriodBO> periodList = this.findPeriodListBySchool();
        model.addObject(CLASSROOM_LIST_PARAMETER, classRoomList);
        model.addObject(CLASS_LIST_PARAMETER, classList);
        model.addObject(PERIOD_LIST_PARAMETER, periodList);
        model.addObject(SAVED_PARAMETER, wasSaved);
        model.addObject(HAS_SERVER_ERRORS_PARAMETER, hasServerErrors);
        model.addObject(MAX_EVALUATION_PARAMETER, this.getMaxEvaluation());
        this.addClassToModel(model, classList, idClass);
        this.addPeriodToModel(model, periodList, idPeriod);
        this.addNoteDefinitionToModel(model, idClass, idPeriod);
        return model;
    }

    private List<ClassRoomBO> buildClassRoomListFromClassList(
            final List<ClassBO> classList) {
        final List<ClassRoomBO> classRoomList = new ArrayList<>();
        for (final ClassBO classBO : classList) {
            final ClassRoomBO classRoom = classBO.getClassRoom();
            if (!classRoomList.contains(classRoom)) {
                classRoomList.add(classRoom);
            }
        }
        return classRoomList;
    }

    private void addClassToModel(final ModelAndView model,
            final List<ClassBO> classList, final Integer idClass) {
        if (idClass != null && idClass > 0) {
            for (final ClassBO classBO : classList) {
                if (classBO.getId().equals(idClass)) {
                    model.addObject(CLASS_PARAMETER, classBO);
                    final ClassRoomBO classRoom = classBO.getClassRoom();
                    model.addObject(CLASSROOM_PARAMETER, classRoom);
                    model.addObject(STUDENT_LIST_PARAMETER,
                            this.sortUserSet(classRoom.getStudentSet()));
                    break;
                }
            }
        }
    }

    private void addPeriodToModel(final ModelAndView model,
            final List<PeriodBO> periodList, final Integer idPeriod) {
        if (idPeriod != null && idPeriod > 0) {
            for (final PeriodBO period : periodList) {
                if (period.getId().equals(idPeriod)) {
                    model.addObject(PERIOD_PARAMETER, period);
                    break;
                }
            }
        }
    }

    private void addNoteDefinitionToModel(final ModelAndView model,
            final Integer idClass, final Integer idPeriod) throws IOException {
        if (idClass != null && idClass > 0 && idPeriod != null && idPeriod > 0) {
            final Set<NoteDefinitionBO> noteDefSet = this.classController
                    .findNoteDefinitionByClass(idClass, idPeriod);
            model.addObject(ACTIVITY_LIST_PARAMETER, noteDefSet);
            final Set<NoteValueBO> noteValueSet = new HashSet<>();
            if (noteDefSet != null) {
                for (final NoteDefinitionBO noteDefinition : noteDefSet) {
                    if (noteDefinition.getNoteValueSet() != null) {
                        noteValueSet.addAll(noteDefinition.getNoteValueSet());
                    }
                }
            }
            model.addObject(ACTIVITY_VALUE_LIST_PARAMETER, noteValueSet);
        }
    }
}
