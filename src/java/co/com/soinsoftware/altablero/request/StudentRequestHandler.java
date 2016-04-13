/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.entity.ClassRoomBO;
import co.com.soinsoftware.altablero.entity.SchoolBO;
import co.com.soinsoftware.altablero.entity.UserBO;
import co.com.soinsoftware.altablero.entity.UserTypeBO;
import static co.com.soinsoftware.altablero.request.AbstractRequestHandler.LOGGER;
import static co.com.soinsoftware.altablero.request.AbstractRequestHandler.USER_ID_REQUEST_PARAM;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlos Rodriguez
 * @since 03/03/2016
 * @version 1.0
 */
@Controller
public class StudentRequestHandler extends AbstractRequestHandler {

    private static final String STUDENT_DEACTIVATE_PAGE = "/admin/estudiantes/edicion/desactivar";
    private static final String STUDENT_EDIT_PAGE = "/admin/estudiantes/edicion";
    private static final String STUDENT_PAGE = "/admin/estudiantes";
    private static final String STUDENT_SAVE_PAGE = "/admin/estudiantes/edicion/guardar";

    private static final String STUDENT_MODEL = "admin/student/list";
    private static final String STUDENT_EDIT_MODEL = "admin/student/edit";

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
                        this.userController.sortUserSet(classRoom.getStudentSet()));
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }
    
    @RequestMapping(value = STUDENT_EDIT_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
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
    
    @RequestMapping(value = STUDENT_SAVE_PAGE, method = RequestMethod.POST)
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
            @RequestParam(value = GUARDIAN1_USER_ID_REQUEST_PARAM, required = true)
            final Integer idUserGuardian1,
            @RequestParam(value = GUARDIAN1_DOCUMENT_TYPE_REQUEST_PARAM, required = true)
            final String docTypeGuardian1,
            @RequestParam(value = GUARDIAN1_DOCUMENT_NUMBER_REQUEST_PARAM, required = true)
            final String docNumberGuardian1,
            @RequestParam(value = GUARDIAN1_NAME_REQUEST_PARAM, required = true)
            final String nameGuardian1,
            @RequestParam(value = GUARDIAN1_LAST_NAME_REQUEST_PARAM, required = true)
            final String lastNameGuardian1,
            @RequestParam(value = GUARDIAN1_ADDRESS_REQUEST_PARAM, required = true)
            final String addressGuardian1,
            @RequestParam(value = GUARDIAN1_PHONE1_REQUEST_PARAM, required = true)
            final String phone1Guardian1,
            @RequestParam(value = GUARDIAN1_PHONE2_REQUEST_PARAM, required = true)
            final String phone2Guardian1,
            @RequestParam(value = GUARDIAN1_GENDER_REQUEST_PARAM, required = true)
            final String genderGuardian1,
            @RequestParam(value = GUARDIAN2_USER_ID_REQUEST_PARAM, required = true)
            final Integer idUserGuardian2,
            @RequestParam(value = GUARDIAN2_DOCUMENT_TYPE_REQUEST_PARAM, required = true)
            final String docTypeGuardian2,
            @RequestParam(value = GUARDIAN2_DOCUMENT_NUMBER_REQUEST_PARAM, required = true)
            final String docNumberGuardian2,
            @RequestParam(value = GUARDIAN2_NAME_REQUEST_PARAM, required = true)
            final String nameGuardian2,
            @RequestParam(value = GUARDIAN2_LAST_NAME_REQUEST_PARAM, required = true)
            final String lastNameGuardian2,
            @RequestParam(value = GUARDIAN2_ADDRESS_REQUEST_PARAM, required = true)
            final String addressGuardian2,
            @RequestParam(value = GUARDIAN2_PHONE1_REQUEST_PARAM, required = true)
            final String phone1Guardian2,
            @RequestParam(value = GUARDIAN2_PHONE2_REQUEST_PARAM, required = true)
            final String phone2Guardian2,
            @RequestParam(value = GUARDIAN2_GENDER_REQUEST_PARAM, required = true)
            final String genderGuardian2) {
        ModelAndView model = null;
        try {
            final SchoolBO school = this.schoolController.findByIdentifier(this.getIdSchool());
            final Set<UserTypeBO> userTypeSet = new HashSet<>();
            userTypeSet.add(this.userTypeController.findBy(UserTypeBO.getStudentCode()));
            final boolean isValidDocNumber = userController.isValidDocumentNumber(
                    idUser, docNumber, this.getIdSchool());
            UserBO user = this.userController.buildUserBO(idUser, docType, docNumber, name,
                    lastName, bornDate, address, phone1, phone2, gender, file, school, userTypeSet);
            final UserBO guardian1 = this.buildGuardian(idUserGuardian1, docTypeGuardian1,
                    docNumberGuardian1, nameGuardian1, lastNameGuardian1, addressGuardian1,
                    phone1Guardian1, phone2Guardian1, genderGuardian1);
            final UserBO guardian2 = this.buildGuardian(idUserGuardian2, docTypeGuardian2,
                    docNumberGuardian2, nameGuardian2, lastNameGuardian2, addressGuardian2,
                    phone1Guardian2, phone2Guardian2, genderGuardian2);
            user.setGuardian1(guardian1);
            user.setGuardian2(guardian2);
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
    
    @RequestMapping(value = STUDENT_DEACTIVATE_PAGE, method = RequestMethod.POST)
    public ModelAndView deactivate(
            @RequestParam(value = USER_ID_REQUEST_PARAM, required = true)
            final Integer idUser) {
        ModelAndView model = null;
        try {
            UserBO user = this.findUserToBeDeactivated(idUser);
            user.setEnabled(false);
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
    
    private ModelAndView buildEditPageModel(final UserBO user, final boolean wasSaved,
            final boolean wasDeactivated, final boolean hasServerErrors,
            final boolean invalidDocNumber) {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(STUDENT_EDIT_MODEL);
            if (user != null) {
                if (user.getPhoto() != null && !user.getPhoto().equals("")) {
                    user.setPhoto(this.userController.getHttpPath(user, this.getIdSchool()));
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
    
    private UserBO buildGuardian(final Integer userId, final String docType,
            final String docNumber, final String name, final String lastName,
            final String address, String phone1, String phone2, final String gender)
            throws IOException {
        final int id = (userId == null) ? 0 : userId;
        phone1 = phone1.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        phone2 = phone2.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        final SchoolBO school = this.schoolController.findByIdentifier(this.getIdSchool());
            final Set<UserTypeBO> userTypeSet = new HashSet<>();
            userTypeSet.add(this.userTypeController.findBy(UserTypeBO.getGuardianCode()));
        final UserBO user = new UserBO();
        user.setId(id);
        user.setDocumentType(docType);
        user.setDocumentNumber(docNumber);
        user.setName(name);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhone1(Long.valueOf(phone1));
        if (!phone2.equals("")) {
            user.setPhone2(Long.valueOf(phone2));
        }
        user.setGender(gender);
        user.setBorn(new Date());
        user.addSchoolToSet(school);
        user.setUserTypeSet(userTypeSet);
        user.setEnabled(true);
        return user;
    }
}
