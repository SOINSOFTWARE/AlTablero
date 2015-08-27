/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.bll;

import co.com.carpco.altablero.entity.ClassRoomBO;
import co.com.carpco.altablero.json.mapper.ClassRoomMapper;
import co.com.carpco.altablero.utils.HttpRequest;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @since 05/06/2015
 */
@Service
public class ClassRoomBLL {
    
    private static final String FIND_BY_METHOD = "classroom/by?";
    private static final String SAVE_METHOD = "classroom/save";
    
    private static final String CLASSROOM_ID_PARAMETER = "&classRoomId=";
    private static final String GRADE_ID_PARAMETER = "&grade=";
    private static final String SCHOOL_ID_PARAMETER = "schoolId=";    
    private static final String TIME_ID_PARAMETER = "&time=";
    private static final String YEAR_PARAMETER = "&year=";
    
    @Autowired
    private HttpRequest httpRequest;
    
    @Autowired
    private ClassRoomMapper classRoomMapper;
    
    public Set<ClassRoomBO> findClassRooms(int idSchool, String year, Integer idGrade, Integer idTime, Integer idClassRoom) throws IOException {
        String methodAndParameters = this.buildFindClassRoomsByUrlMethod(idSchool, year, idGrade, idTime, idClassRoom);
        String response = httpRequest.sendGet(methodAndParameters);
        return classRoomMapper.getObjectSetFromJSON(response);
    }
    
    private String buildFindClassRoomsByUrlMethod(int idSchool, String year, Integer idGrade, Integer idTime, Integer idClassRoom) {
        StringBuilder urlMethod = new StringBuilder(FIND_BY_METHOD);
        urlMethod.append(SCHOOL_ID_PARAMETER);
        urlMethod.append(idSchool);
        if (year != null && !year.isEmpty()) {
            urlMethod.append(YEAR_PARAMETER);
            urlMethod.append(year);
        }
        if (idGrade != null && idGrade > 0) {
            urlMethod.append(GRADE_ID_PARAMETER);
            urlMethod.append(idGrade);
        }
        
        if (idTime != null && idTime > 0) {
            urlMethod.append(TIME_ID_PARAMETER);
            urlMethod.append(idTime);
        }
        
        if (idClassRoom != null && idClassRoom > 0) {
            urlMethod.append(CLASSROOM_ID_PARAMETER);
            urlMethod.append(idClassRoom);
        }
        return urlMethod.toString();
    }
    
    public ClassRoomBO saveClassRoom(ClassRoomBO classRoom) throws IOException {
        String jsonObject = this.writeValueAsString(classRoom);
        String response = httpRequest.sendPost(SAVE_METHOD, jsonObject);
        return classRoomMapper.getObjectFromJSON(response);
    }
    
    private String writeValueAsString(ClassRoomBO classRoom) {
        String jsonObject = null;
        try {
            jsonObject = ClassRoomMapper.JSON_WRITER.writeValueAsString(classRoom);
        } catch (IOException ex) {
            Logger.getLogger(ClassRoomBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject;
    }
}
