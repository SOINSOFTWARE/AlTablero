/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.UserBO;
import co.com.soinsoftware.altablero.json.mapper.UserMapper;
import co.com.soinsoftware.altablero.utils.HttpRequest;
import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @since 15/06/2015
 */
@Service
public class UserBLL {
    
    private static final String FIND_BY_DOCUMENT_METHOD = "user/by?documentNumber=";
    private static final String FIND_BY_TYPE_METHOD = "user/byType?";
    private static final String FIND_TEACHERS_FREE_METHOD = "user/teacherFree?";
    
    private static final String SCHOOL_ID_PARAMETER = "schoolId=";
    private static final String TYPE_CODE_PARAMETER = "&userTypeCode=";
    
    @Autowired
    private HttpRequest httpRequest;
    
    @Autowired
    private UserMapper userMapper;
    
    public UserBO findUserByDocument(String documentNumber) throws IOException {
        String methodAndParameters = FIND_BY_DOCUMENT_METHOD + documentNumber;
        String response = httpRequest.sendGet(methodAndParameters);
        return userMapper.getObjectFromJSON(response);
    }
    
    public Set<UserBO> findUsersByUserType(int idSchool, String cdUserType) throws IOException {
        String methodAndParameters = this.buildFindUserByTypeUrlMethod(idSchool, cdUserType);
        String response = httpRequest.sendGet(methodAndParameters);
        return userMapper.getObjectSetFromJSON(response);
    }
    
    public Set<UserBO> findTeacherNoDirectors(int idSchool) throws IOException {
        String methodAndParameters = this.buildTeacherNoDirectorsUrlMethod(idSchool);
        String response = httpRequest.sendGet(methodAndParameters);
        return userMapper.getObjectSetFromJSON(response);
    }
    
    private String buildFindUserByTypeUrlMethod(int idSchool, String cdUserType) {
        StringBuilder urlMethod = new StringBuilder(FIND_BY_TYPE_METHOD);
        urlMethod.append(SCHOOL_ID_PARAMETER);
        urlMethod.append(idSchool);
        urlMethod.append(TYPE_CODE_PARAMETER);
        urlMethod.append(cdUserType);
        return urlMethod.toString();
    }
    
    private String buildTeacherNoDirectorsUrlMethod(int idSchool) {
        StringBuilder urlMethod = new StringBuilder(FIND_TEACHERS_FREE_METHOD);
        urlMethod.append(SCHOOL_ID_PARAMETER);
        urlMethod.append(idSchool);
        return urlMethod.toString();
    }
}
