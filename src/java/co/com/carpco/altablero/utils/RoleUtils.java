/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.utils;

import co.com.carpco.altablero.hibernate.entities.BzUser;
import co.com.carpco.altablero.hibernate.entities.BzUserXuserType;
import co.com.carpco.altablero.hibernate.entities.CnUserType;
import co.com.carpco.altablero.hibernate.entities.CnUsertTypeXaccess;
import java.util.HashSet;
import java.util.Set;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Carlos Rodriguez
 */
public class RoleUtils {
    
    private static RoleUtils instance;
    
    private RoleUtils() {
        super();
    }
    
    public static RoleUtils getInstance() {
        if (instance == null) {
            instance = new RoleUtils();
        }
        return instance;
    }
    
    public ModelAndView createModelWithUserDetails(BzUser bzUser) {
        Set<BzUserXuserType> bzUserXUserTypeSet = bzUser.getBzUserXuserTypes();
            
        ModelAndView model = new ModelAndView();

        model.addObject("userFirstName", bzUser.getName());
        model.addObject("username", bzUser.getName() + " " + bzUser.getLastName());

        if (bzUser.getGender().equals("Masculino")) {
            model.addObject("avatar", "avatar5");
        } else {
            model.addObject("avatar", "avatar2");
        }

        boolean viewGradeMenu = false;
        boolean viewSubjectMenu = false;
        boolean viewTeacherMenu = false;
        boolean viewStudentMenu = false;
        Set<String> accessList = new HashSet();

        for (BzUserXuserType bzUserXUserType : bzUserXUserTypeSet) {

            CnUserType cnUserType = bzUserXUserType.getCnUserType();
            switch (cnUserType.getCode()) {
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

            for (CnUsertTypeXaccess cnUserTypeXAccess : cnUserType.getCnUsertTypeXaccesses()) {
                accessList.add(cnUserTypeXAccess.getCnAccess().getCode());
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