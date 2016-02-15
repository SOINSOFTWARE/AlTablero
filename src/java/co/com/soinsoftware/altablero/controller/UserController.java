/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.UserBLL;
import co.com.soinsoftware.altablero.entity.UserBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2016
 * @version 1.0
 */
@Service
public class UserController {

    @Autowired
    private UserBLL userBLL;

    public List<UserBO> findTeachersNotGroupDirector(final int idSchool,
            final UserBO currentDirector) throws IOException {
        List<UserBO> userList = null;
        final Set<UserBO> teacherAvailableSet = userBLL.findTeacherNoDirectors(idSchool);
        userList = new ArrayList<>();
        if (teacherAvailableSet != null) {
            userList.addAll(teacherAvailableSet);
        }
        if (currentDirector != null) {
            userList.add(currentDirector);
        }
        Collections.sort(userList);
        return userList;
    }
    
    public List<UserBO> findUsersByUserType(final int idSchool, final String cdUserType)
            throws IOException {
        List<UserBO> userList = new ArrayList<>();
        final Set<UserBO> userSet = userBLL.findUsersByUserType(idSchool, cdUserType);
        if (userSet != null) {
            userList = new ArrayList<>(userSet);
            Collections.sort(userList);
        }
        return userList;
    }
}
