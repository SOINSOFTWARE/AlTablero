/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.UserBO;
import co.com.soinsoftware.altablero.json.mapper.UserMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 15/06/2015
 * @version 1.0
 */
@Service
public class UserBLL extends AbstractBLL {

    @Autowired
    private UserMapper userMapper;

    public UserBO findUserByDocument(final String documentNumber)
            throws IOException {
        final StringBuilder method = new StringBuilder(MODULE_USER + PATH_BY);
        method.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_DOCUMENT_NUMBER,
                documentNumber));
        final String methodAndParameters = method.toString();
        final String response = httpRequest.sendGet(methodAndParameters);
        return userMapper.getObjectFromJSON(response);
    }

    public Set<UserBO> findUsersByUserType(final int idSchool, final String cdUserType)
            throws IOException {
        final String methodAndParameters = this.buildFindUserByTypeUrlMethod(
                idSchool, cdUserType);
        return this.sendGetToReceiveSet(methodAndParameters);
    }

    public Set<UserBO> findTeacherNoDirectors(final int idSchool)
            throws IOException {
        final String methodAndParameters = this.buildTeacherNoDirectorsUrlMethod(
                idSchool);
        return this.sendGetToReceiveSet(methodAndParameters);
    }

    public Set<UserBO> findStudentsNotLinked(final int idSchool, final Integer idGrade,
            final Integer idClassRoom) throws IOException {
        final String methodAndParameters = this.buildStudentsNotLinkedUrlMethod(
                idSchool, idGrade, idClassRoom);
        return this.sendGetToReceiveSet(methodAndParameters);
    }
    
    public List<UserBO> sortUserSet(final Set<UserBO> userSet) {
        List<UserBO> userList = new ArrayList<>(userSet);
        Collections.sort(userList);
        return userList;
    }
    
    private Set<UserBO> sendGetToReceiveSet(final String methodAndParameters) throws IOException {
        final String response = httpRequest.sendGet(methodAndParameters);
        return userMapper.getObjectSetFromJSON(response);
    }

    private String buildFindUserByTypeUrlMethod(final int idSchool,
            final String cdUserType) {
        final String method = MODULE_USER + PATH_BY_TYPE;
        final StringBuilder urlMethod = new StringBuilder(method);
        urlMethod.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_SCHOOL_ID,
                idSchool));
        urlMethod.append(buildRequestParameter(CONCAT, PARAMETER_TYPE_CODE,
                cdUserType));
        return urlMethod.toString();
    }

    private String buildTeacherNoDirectorsUrlMethod(final int idSchool) {
        final String method = MODULE_USER + PATH_TEACHER_NOT_DIRECTORS;
        final StringBuilder urlMethod = new StringBuilder(method);
        urlMethod.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_SCHOOL_ID,
                idSchool));
        return urlMethod.toString();
    }

    private String buildStudentsNotLinkedUrlMethod(final int idSchool,
            final Integer idGrade, final Integer idClassRoom) {
        final String method = MODULE_USER + PATH_STUDENTS_NOT_LINKED;
        final StringBuilder urlMethod = new StringBuilder(method);
        urlMethod.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_SCHOOL_ID,
                idSchool));
        if (idGrade != null && idGrade > 0) {
            urlMethod.append(buildRequestParameter(CONCAT, PARAMETER_GRADE, idGrade));
        }
        if (idClassRoom != null && idClassRoom > 0) {
            urlMethod.append(buildRequestParameter(CONCAT, PARAMETER_CLASSROOM_ID, idClassRoom));
        }
        return urlMethod.toString();
    }
}
