/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.utils;

import co.com.soinsoftware.altablero.controller.UserController;
import co.com.soinsoftware.altablero.entity.AccessBO;
import co.com.soinsoftware.altablero.entity.UserBO;
import static co.com.soinsoftware.altablero.entity.UserBO.femaleAvatar;
import static co.com.soinsoftware.altablero.entity.UserBO.maleAvatar;
import static co.com.soinsoftware.altablero.entity.UserBO.maleGender;
import co.com.soinsoftware.altablero.entity.UserTypeBO;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
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
    private static final String USER_PHOTO_ATTRIBUTE_ONLY = "isAvatar";
    private static final String USER_ACCESS_ATTRIBUTE = "accessList";

    private static final String MENU_GRADE_ATTRIBUTE = "canViewGradeMenu";
    private static final String MENU_SUBJECT_ATTRIBUTE = "canViewSubjectMenu";
    private static final String MENU_TEACHER_ATTRIBUTE = "canViewTeacherMenu";
    private static final String MENU_STUDENT_ATTRIBUTE = "canViewStudentMenu";

    @Autowired
    private UserController userController;

    public ModelAndView createModelWithUserDetails(final UserBO user,
            final int idSchool) throws IOException {
        if (user == null) {
            throw new IOException("User not found");
        }
        return this.buildModelUsingUserBO(user, idSchool);
    }

    private ModelAndView buildModelUsingUserBO(final UserBO user, final int idSchool) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            final String fullName = user.getName() + " " + user.getLastName();
            model.addObject(USER_FIRST_NAME_ATTRIBUTE, user.getName());
            model.addObject(USER_NAME_ATTRIBUTE, fullName);
            model.addObject(USER_ACCESS_ATTRIBUTE, this.getAccessCodeList(user));
            model.addObject(MENU_GRADE_ATTRIBUTE, user.canViewGradeMenu());
            model.addObject(MENU_SUBJECT_ATTRIBUTE, user.canViewSubjectMenu());
            model.addObject(MENU_TEACHER_ATTRIBUTE, user.canViewTeacherMenu());
            model.addObject(MENU_STUDENT_ATTRIBUTE, user.canViewStudentMenu());
            this.addUserPhotoToModel(model, user, idSchool);
        }
        return model;
    }

    public Set<String> getAccessCodeList(final UserBO user) {
        Set<String> accessList = new HashSet();
        if (user.getUserTypeSet() != null) {
            for (UserTypeBO userType : user.getUserTypeSet()) {
                final Set<String> accessCodeList = this.getAccessCodeList(userType);
                accessList.addAll(accessCodeList);
            }
        }
        return accessList;
    }

    public Set<String> getAccessCodeList(final UserTypeBO userType) {
        Set<String> accessList = new HashSet();
        for (AccessBO access : userType.getAccessSet()) {
            accessList.add(access.getCode());
        }
        return accessList;
    }
    
    private void addUserPhotoToModel(final ModelAndView model, final UserBO user,
            final int idSchool) {
        String photoAttr = null;
        boolean isAvatar = true;
        if (user.getPhoto() == null && idSchool > 0) {
            photoAttr = user.getGender().equals(maleGender)
                    ? maleAvatar : femaleAvatar;
        } else {
            photoAttr = this.userController.getHttpPath(user, idSchool);
            isAvatar = false;
        }
        model.addObject(USER_PHOTO_ATTRIBUTE, photoAttr);
        model.addObject(USER_PHOTO_ATTRIBUTE_ONLY, isAvatar);
    }
}
