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
    
    private static final String SCHOOL_ID_PARAMETER = "schoolId=";
    private static final String GRADE_ID_PARAMETER = "&grade=";    
    private static final String YEAR_PARAMETER = "&year=";
    private static final String CLASSROOM_ID_PARAMETER = "&classRoomId=";
    
    @Autowired
    private HttpRequest httpRequest;
    
    @Autowired
    private ClassRoomMapper classRoomMapper;
    
    public Set<ClassRoomBO> findClassRooms(int idSchool, String year, Integer idGrade, Integer idClassRoom) throws IOException {
        String methodAndParameters = this.buildFindClassRoomsByUrlMethod(idSchool, year, idGrade, idClassRoom);
        String response = httpRequest.sendGet(methodAndParameters);
        return classRoomMapper.getObjectSetFromJSON(response);
    }
    
    private String buildFindClassRoomsByUrlMethod(int idSchool, String year, Integer idGrade, Integer idClassRoom) {
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
        
        if (idClassRoom != null && idClassRoom > 0) {
            urlMethod.append(CLASSROOM_ID_PARAMETER);
            urlMethod.append(idClassRoom);
        }
        return urlMethod.toString();
    }
}
