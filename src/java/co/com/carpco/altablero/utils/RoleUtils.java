/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.utils;

import co.com.carpco.altablero.bll.UserBLL;
import co.com.carpco.altablero.entity.UserBO;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Carlos Rodriguez
 */
@Service
public class RoleUtils {
    
    private static final String USER_FIRST_NAME_ATTRIBUTE = "userFirstName";
    private static final String USER_NAME_ATTRIBUTE = "username";
    private static final String USER_PHOTO_ATTRIBUTE = "avatar";
    private static final String USER_ACCESS_ATTRIBUTE = "accessList";
    
    private static final String MENU_GRADE_ATTRIBUTE = "canViewGradeMenu";
    private static final String MENU_SUBJECT_ATTRIBUTE = "canViewSubjectMenu";
    private static final String MENU_TEACHER_ATTRIBUTE = "canViewTeacherMenu";
    private static final String MENU_STUDENT_ATTRIBUTE = "canViewStudentMenu";
    
    @Autowired
    private UserBLL userBLL;

    public ModelAndView createModelWithUserDetails(String documentNumber) throws IOException {
        UserBO user = this.userBLL.findUserByDocument(documentNumber);
        return this.buildModelUsingUserBO(user);
    }
    
    private ModelAndView buildModelUsingUserBO(UserBO user) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject(USER_FIRST_NAME_ATTRIBUTE, user.getName());
            model.addObject(USER_NAME_ATTRIBUTE, user.getCompleteName());
            model.addObject(USER_PHOTO_ATTRIBUTE, user.getDefaultAvatar());
            model.addObject(USER_ACCESS_ATTRIBUTE, user.getAccessCodeList());
            model.addObject(MENU_GRADE_ATTRIBUTE, user.canViewGradeMenu());
            model.addObject(MENU_SUBJECT_ATTRIBUTE, user.canViewSubjectMenu());
            model.addObject(MENU_TEACHER_ATTRIBUTE, user.canViewTeacherMenu());
            model.addObject(MENU_STUDENT_ATTRIBUTE, user.canViewStudentMenu());
        }
        return model;
    }
}