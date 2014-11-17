/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.utils;

import co.com.carpco.altablero.bo.AccessBO;
import co.com.carpco.altablero.bo.UserBO;
import co.com.carpco.altablero.bo.UserTypeBO;
import co.com.carpco.altablero.hibernate.bll.UserBll;
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
    
    @Autowired
    UserBll userBll;
    
    public ModelAndView createModelWithUserDetails(String documentNumber) {
        UserBO user = userBll.getUserByDocumentNumber(documentNumber);
        ModelAndView model = new ModelAndView();

        model.addObject("userFirstName", user.getName());
        model.addObject("username", user.getName() + " " + user.getLastName());

        if (user.getGender().equals("Masculino")) {
            model.addObject("avatar", "avatar5");
        } else {
            model.addObject("avatar", "avatar2");
        }

        boolean viewGradeMenu = false;
        boolean viewSubjectMenu = false;
        boolean viewTeacherMenu = false;
        boolean viewStudentMenu = false;
        Set<String> accessList = new HashSet();

        for (UserTypeBO userType : user.getUserTypeSet()) {

            switch (userType.getCode()) {
                case "RCTOR":
                    viewGradeMenu = true;
                    viewSubjectMenu = true;
                    viewTeacherMenu = true;
                    viewStudentMenu = true;
                    break;
                case "COORD":
                    viewSubjectMenu = true;
                    viewTeacherMenu = true;
                    viewStudentMenu = true;
                    break;
                case "PROFE":
                    viewTeacherMenu = true;
                    viewStudentMenu = true;
                    break;
                case "ESTUD":
                    viewStudentMenu = true;
                    break;
            }

            for (AccessBO access : userType.getAccessSet()) {
                accessList.add(access.getCode());
            }
        }
        model.addObject("canViewGradeMenu", viewGradeMenu);
        model.addObject("canViewSubjectMenu", viewSubjectMenu);
        model.addObject("canViewTeacherMenu", viewTeacherMenu);
        model.addObject("canViewStudentMenu", viewStudentMenu);
        model.addObject("accessList", accessList);

        return model;
    }
}