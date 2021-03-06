/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.controller.ClassController;
import co.com.soinsoftware.altablero.controller.ClassRoomController;
import co.com.soinsoftware.altablero.controller.GradeController;
import co.com.soinsoftware.altablero.controller.NoteDefinitionController;
import co.com.soinsoftware.altablero.controller.NoteValueController;
import co.com.soinsoftware.altablero.controller.PeriodController;
import co.com.soinsoftware.altablero.controller.ReportController;
import co.com.soinsoftware.altablero.controller.SchoolController;
import co.com.soinsoftware.altablero.controller.TimeController;
import co.com.soinsoftware.altablero.controller.UserController;
import co.com.soinsoftware.altablero.controller.UserTypeController;
import co.com.soinsoftware.altablero.controller.YearController;
import co.com.soinsoftware.altablero.entity.ClassBO;
import co.com.soinsoftware.altablero.entity.ClassRoomBO;
import co.com.soinsoftware.altablero.entity.NoteValueConfigurationBO;
import co.com.soinsoftware.altablero.entity.PeriodBO;
import co.com.soinsoftware.altablero.entity.SchoolBO;
import co.com.soinsoftware.altablero.entity.UserBO;
import co.com.soinsoftware.altablero.entity.UserTypeBO;
import co.com.soinsoftware.altablero.utils.AuthenticationUtils;
import co.com.soinsoftware.altablero.utils.RoleUtils;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Carlos Rodriguez
 */
public abstract class AbstractRequestHandler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractRequestHandler.class);

    protected static final String ACTIVITY_LIST_PARAMETER = "activities";
    protected static final String ACTIVITY_VALUE_LIST_PARAMETER = "values";
    protected static final String CLASS_PARAMETER = "classBO";
    protected static final String CLASS_LIST_PARAMETER = "classes";
    protected static final String CLASSROOM_PARAMETER = "classroom";
    protected static final String CLASSROOM_LIST_PARAMETER = "classrooms";
    protected static final String CURRENT_CLASSROOM_LIST_PARAMETER = "currentClassrooms";
    protected static final String CURRENT_YEAR_PARAMETER = "currentYear";
    protected static final String DEACTIVATED_PARAMETER = "deactivated";
    protected static final String GRADE_PARAMETER = "grade";
    protected static final String GRADE_LIST_PARAMETER = "grades";
    protected static final String GROUP_DIRECTOR_PARAMETER = "groupDirector";
    protected static final String HAS_SERVER_ERRORS_PARAMETER = "hasServerErrors";
    protected static final String INVALIDCODE_PARAMETER = "invalidCode";
    protected static final String MAX_EVALUATION_PARAMETER = "maxEvaluation";
    protected static final String PERIOD_PARAMETER = "period";
    protected static final String PERIOD_LIST_PARAMETER = "periods";
    protected static final String SAVED_PARAMETER = "saved";
    protected static final String SHOW_SEARCH_PARAMETER = "showSearch";
    protected static final String STUDENT_LIST_PARAMETER = "students";
    protected static final String TEACHER_LIST_PARAMETER = "teachers";
    protected static final String TIME_LIST_PARAMETER = "times";
    protected static final String USER_PARAMETER = "user";
    protected static final String YEAR_PARAMETER = "year";
    protected static final String YEAR_LIST_PARAMETER = "years";

    protected static final String ADDRESS_REQUEST_PARAM = "address";
    protected static final String BORN_DATE_REQUEST_PARAM = "bornDate";
    protected static final String CLASS_REQUEST_PARAM = "classBO";
    protected static final String CLASS_ID_REQUEST_PARAM = "classId";
    protected static final String CLASSROOM_REQUEST_PARAM = "classroom";
    protected static final String CLASSROOM_ID_REQUEST_PARAM = "classroomId";
    protected static final String CODE_REQUEST_PARAM = "code";
    protected static final String COORDINATOR_REQUEST_PARAM = "coordinator";
    protected static final String DIRECTOR_REQUEST_PARAM = "director";
    protected static final String DOCUMENT_NUMBER_REQUEST_PARAM = "documentNumber";
    protected static final String DOCUMENT_TYPE_REQUEST_PARAM = "documentType";
    protected static final String FILE_UPLOAD_REQUEST_PARAM = "fileUpload";
    protected static final String GENDER_REQUEST_PARAM = "gender";
    protected static final String GRADE_REQUEST_PARAM = "grade";
    protected static final String GRADE_ID_REQUEST_PARAM = "gradeId";
    protected static final String GUARDIAN1_ADDRESS_REQUEST_PARAM = "guardian1Address";
    protected static final String GUARDIAN1_DOCUMENT_NUMBER_REQUEST_PARAM = "guardian1DocumentNumber";
    protected static final String GUARDIAN1_DOCUMENT_TYPE_REQUEST_PARAM = "guardian1DocType";
    protected static final String GUARDIAN1_GENDER_REQUEST_PARAM = "guardian1Gender";
    protected static final String GUARDIAN1_LAST_NAME_REQUEST_PARAM = "guardian1LastName";
    protected static final String GUARDIAN1_NAME_REQUEST_PARAM = "guardian1Name";
    protected static final String GUARDIAN1_PHONE1_REQUEST_PARAM = "guardian1Phone1";
    protected static final String GUARDIAN1_PHONE2_REQUEST_PARAM = "guardian1Phone2";
    protected static final String GUARDIAN1_USER_ID_REQUEST_PARAM = "guardian1Id";
    protected static final String GUARDIAN2_ADDRESS_REQUEST_PARAM = "guardian2Address";
    protected static final String GUARDIAN2_DOCUMENT_NUMBER_REQUEST_PARAM = "guardian2DocumentNumber";
    protected static final String GUARDIAN2_DOCUMENT_TYPE_REQUEST_PARAM = "guardian2DocType";
    protected static final String GUARDIAN2_GENDER_REQUEST_PARAM = "guardian2Gender";
    protected static final String GUARDIAN2_LAST_NAME_REQUEST_PARAM = "guardian2LastName";
    protected static final String GUARDIAN2_NAME_REQUEST_PARAM = "guardian2Name";
    protected static final String GUARDIAN2_PHONE1_REQUEST_PARAM = "guardian2Phone1";
    protected static final String GUARDIAN2_PHONE2_REQUEST_PARAM = "guardian2Phone2";
    protected static final String GUARDIAN2_USER_ID_REQUEST_PARAM = "guardian2Id";
    protected static final String LAST_NAME_REQUEST_PARAM = "lastName";
    protected static final String NAME_REQUEST_PARAM = "name";
    protected static final String OBJECT_AS_STRING_REQUEST_PARAM = "objectStr";
    protected static final String PERIOD_REQUEST_PARAM = "period";
    protected static final String PERIOD_ID_REQUEST_PARAM = "periodId";
    protected static final String PHONE1_REQUEST_PARAM = "phone1";
    protected static final String PHONE2_REQUEST_PARAM = "phone2";
    protected static final String TIME_REQUEST_PARAM = "time";
    protected static final String USER_ID_REQUEST_PARAM = "userId";
    protected static final String YEAR_REQUEST_PARAM = "year";

    @Autowired
    private RoleUtils roleUtils;

    @Autowired
    protected ClassController classController;

    @Autowired
    protected ClassRoomController classRoomController;

    @Autowired
    protected GradeController gradeController;

    @Autowired
    protected NoteDefinitionController noteDefController;

    @Autowired
    protected NoteValueController noteValueController;

    @Autowired
    protected PeriodController periodController;

    @Autowired
    protected ReportController reportController;

    @Autowired
    protected SchoolController schoolController;

    @Autowired
    protected TimeController timeController;

    @Autowired
    protected UserController userController;

    @Autowired
    protected UserTypeController userTypeController;

    @Autowired
    protected YearController yearController;

    protected ModelAndView buildModelAndView(final UserBO user) throws IOException {
        final ModelAndView model = this.roleUtils.createModelWithUserDetails(
                user, this.getSchool(user));
        boolean isGroupDirector = false;
        if (user != null && this.isTeacher(user) && this.isGroupDirector(user)) {
            isGroupDirector = true;
        }
        model.addObject(GROUP_DIRECTOR_PARAMETER, isGroupDirector);
        return model;
    }

    protected UserBO getLogeduser() {
        final String documentNumber = AuthenticationUtils.getDocumentNumberFromAuthentication();
        UserBO user = null;
        try {
            user = this.userController.findUserByDocument(documentNumber);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
        return user;
    }

    protected int getIdSchool(final UserBO user) {
        final Set<SchoolBO> schoolSet = (user != null) ? user.getSchoolSet() : null;
        int idSchool = 0;
        if (schoolSet != null) {
            for (final SchoolBO school : schoolSet) {
                idSchool = school.getId();
            }
        }
        return idSchool;
    }
    
    protected SchoolBO getSchool(final UserBO user) {
        final Set<SchoolBO> schoolSet = (user != null) ? user.getSchoolSet() : null;
        if (schoolSet != null) {
            for (final SchoolBO school : schoolSet) {
                return school;
            }
        }
        return null;
    }

    protected BigDecimal getMaxEvaluation(final UserBO user) {
        final Set<SchoolBO> schoolSet = (user != null) ? user.getSchoolSet() : null;
        BigDecimal maxEvaluation = null;
        if (schoolSet != null) {
            for (final SchoolBO school : schoolSet) {
                for (final NoteValueConfigurationBO noteValue : school.getNote().getNoteValueSet()) {
                    if (maxEvaluation == null
                            || maxEvaluation.doubleValue() < noteValue.getRangeEnd().doubleValue()) {
                        maxEvaluation = noteValue.getRangeEnd();
                    }
                }
            }
        }
        return maxEvaluation;
    }

    protected void addYearListToModel(final ModelAndView model)
            throws IOException {
        model.addObject(YEAR_LIST_PARAMETER, yearController.findAll());
    }

    protected void addTimeListToModel(final ModelAndView model)
            throws IOException {
        model.addObject(TIME_LIST_PARAMETER, timeController.findAll());
    }

    protected void addGradeListToModel(final ModelAndView model)
            throws IOException {
        model.addObject(GRADE_LIST_PARAMETER, gradeController.findAll());
    }

    protected void addTeacherNotDirectorListToModel(final ModelAndView model,
            final UserBO currentDirector, final UserBO logedUser) throws IOException {
        final int idSchool = this.getIdSchool(logedUser);
        final List<UserBO> teacherList
                = userController.findTeachersNotGroupDirector(idSchool, currentDirector);
        model.addObject(TEACHER_LIST_PARAMETER, teacherList);
    }

    protected void addClassRoomListToModel(final ModelAndView model, final String year,
            final Integer grade, final UserBO user) throws IOException {
        model.addObject(CLASSROOM_LIST_PARAMETER,
                classRoomController.findClassRooms(year, grade, this.getIdSchool(user)));
    }

    protected void addClassListToModel(final ModelAndView model, final int idClassRoom,
            final int idTeacher, final boolean addDefaultData, final UserBO logedUser)
            throws IOException {
        final List<ClassBO> classList = classController.findClasses(
                this.getIdSchool(logedUser), idClassRoom, idTeacher, addDefaultData);
        model.addObject(CLASS_LIST_PARAMETER, classList);
    }

    protected void addTeacherListToModel(final ModelAndView model,
            final UserBO user) throws IOException {
        final int idSchool = this.getIdSchool(user);
        final String teacherCode = UserTypeBO.getTeacherCode();
        List<UserBO> teacherList = null;
        if (this.isTeacher(user) && !this.isCoordinator(user)) {
            teacherList = new ArrayList<>();
            teacherList.add(user);
        } else {
            teacherList = userController.findUsersByUserType(idSchool, teacherCode);
        }
        model.addObject(TEACHER_LIST_PARAMETER, teacherList);
    }

    protected void addStudentsNotLinkedToModel(final ModelAndView model,
            final Integer idGrade, final Integer idClassRoom, final UserBO user)
            throws IOException {
        final int idSchool = this.getIdSchool(user);
        final List<UserBO> userList = userController.findStudentsNotLinked(idSchool, idGrade, idClassRoom);
        model.addObject(STUDENT_LIST_PARAMETER, userList);
    }

    protected UserBO findUserByIdentifier(final Integer idUser) throws IOException {
        UserBO user = null;
        if (idUser != null) {
            user = this.userController.findUserByIdentifier(idUser);
        }
        return user;
    }

    protected UserBO findUserToBeDeactivated(final UserBO logedUser, final int idUser)
            throws IOException {
        final SchoolBO school = this.schoolController.findByIdentifier(
                this.getIdSchool(logedUser));
        final UserBO user = this.findUserByIdentifier(idUser);
        if (user != null && user.getSchoolSet() != null && school != null
                && user.getSchoolSet().contains(school)) {
            for (final SchoolBO schoolFromUser : user.getSchoolSet()) {
                if (schoolFromUser.equals(school)) {
                    schoolFromUser.setEnabled(false);
                    break;
                }
            }
        }
        return user;
    }

    protected List<PeriodBO> findPeriodListBySchool(final UserBO user) throws IOException {
        return periodController.findAll(this.getIdSchool(user));
    }

    protected ClassRoomBO findClassRoom(final int idSchool, final Integer idClassRoom)
            throws IOException {
        return this.classRoomController.findClassRoom(idSchool, idClassRoom);
    }

    protected List<UserBO> sortUserSet(final Set<UserBO> userSet) {
        return this.userController.sortUserSet(userSet);
    }

    protected boolean isTeacher(final UserBO user) {
        boolean isTeacher = false;
        if (user != null && user.getUserTypeSet() != null) {
            for (final UserTypeBO userType : user.getUserTypeSet()) {
                if (userType.isTeacher()) {
                    isTeacher = true;
                    break;
                }
            }
        }
        return isTeacher;
    }

    protected boolean isCoordinator(final UserBO user) {
        boolean isCoordinator = false;
        if (user != null && user.getUserTypeSet() != null) {
            for (final UserTypeBO userType : user.getUserTypeSet()) {
                if (userType.isCoordinator()) {
                    isCoordinator = true;
                    break;
                }
            }
        }
        return isCoordinator;
    }

    protected boolean isStudent(final UserBO user) {
        boolean isStudent = false;
        if (user != null && user.getUserTypeSet() != null) {
            for (final UserTypeBO userType : user.getUserTypeSet()) {
                if (userType.isStudent()) {
                    isStudent = true;
                    break;
                }
            }
        }
        return isStudent;
    }

    protected boolean isGuardian(final UserBO user) {
        boolean isGuardian = false;
        if (user != null && user.getUserTypeSet() != null) {
            for (final UserTypeBO userType : user.getUserTypeSet()) {
                if (userType.isGuardian()) {
                    isGuardian = true;
                    break;
                }
            }
        }
        return isGuardian;
    }

    protected ClassRoomBO getGroupDirectorClassRoom(final UserBO user) throws IOException {
        ClassRoomBO groupDirClassRoom = null;
        final String currentYear = yearController.getCurrentYearString();
        final List<ClassRoomBO> classRoomList = this.classRoomController.findClassRooms(
                currentYear, 0, this.getIdSchool(user));
        for (final ClassRoomBO classRoom : classRoomList) {
            if (classRoom.getUserBO().equals(user)) {
                groupDirClassRoom = classRoom;
                break;
            }
        }
        return groupDirClassRoom;
    }

    protected InputStream generateReports(final int idSchool, final int idClassRoom,
            final int idPeriod) throws IOException {
        return this.reportController.generateReports(idSchool, idClassRoom, idPeriod);
    }

    protected boolean isGroupDirector(final UserBO user) throws IOException {
        final ClassRoomBO groupDirClassRoom = this.getGroupDirectorClassRoom(user);
        return groupDirClassRoom != null;
    }
}
