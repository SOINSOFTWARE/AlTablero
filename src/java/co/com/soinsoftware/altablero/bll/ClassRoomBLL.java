/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.ClassRoomBO;
import co.com.soinsoftware.altablero.json.mapper.ClassRoomMapper;
import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 05/06/2015
 * @version 1.0
 */
@Service
public class ClassRoomBLL extends AbstractBLL {

    @Autowired
    private ClassRoomMapper classRoomMapper;

    public Set<ClassRoomBO> findClassRooms(final int idSchool, final String year,
            final Integer idGrade, final Integer idTime, final Integer idClassRoom)
            throws IOException {
        final String methodAndParameters
                = this.buildFindClassRoomsByUrlMethod(
                        idSchool, year, idGrade, idTime, idClassRoom);
        final String response = httpRequest.sendGet(methodAndParameters);
        return classRoomMapper.getObjectSetFromJSON(response);
    }

    private String buildFindClassRoomsByUrlMethod(final int idSchool, final String year,
            final Integer idGrade, final Integer idTime, final Integer idClassRoom) {
        final String method = MODULE_CLASSROOM + PATH_BY;
        final StringBuilder urlMethod = new StringBuilder(method);
        urlMethod.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_SCHOOL_ID,
                idSchool));
        if (year != null && !year.isEmpty()) {
            urlMethod.append(buildRequestParameter(CONCAT, PARAMETER_YEAR, year));
        }
        if (idGrade != null && idGrade > 0) {
            urlMethod.append(buildRequestParameter(CONCAT, PARAMETER_GRADE,
                    idGrade));
        }

        if (idTime != null && idTime > 0) {
            urlMethod.append(buildRequestParameter(CONCAT, PARAMETER_TIME, idTime));
        }

        if (idClassRoom != null && idClassRoom > 0) {
            urlMethod.append(buildRequestParameter(CONCAT, PARAMETER_CLASSROOM_ID,
                    idClassRoom));
        }
        return urlMethod.toString();
    }

    public ClassRoomBO saveClassRoom(final ClassRoomBO classRoom)
            throws IOException {
        final String jsonObject = this.writeValueAsString(classRoom);
        final String method = MODULE_CLASSROOM + PATH_SAVE;
        final String response = httpRequest.sendPost(method, jsonObject);
        return classRoomMapper.getObjectFromJSON(response);
    }

    private String writeValueAsString(final ClassRoomBO classRoom) {
        String jsonObject = null;
        try {
            jsonObject = ClassRoomMapper.JSON_WRITER.writeValueAsString(classRoom);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return jsonObject;
    }

    public boolean IsValidCode(final int idSchool, final int idClassRoom,
            final String code) throws IOException {
        final String method = MODULE_CLASSROOM + PATH_VALIDATE;
        final StringBuilder urlMethod = new StringBuilder(method);
        urlMethod.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_SCHOOL_ID,
                idSchool));
        urlMethod.append(buildRequestParameter(CONCAT, PARAMETER_CODE, code));
        urlMethod.append(buildRequestParameter(CONCAT, PARAMETER_CLASSROOM_ID,
                idClassRoom));
        final String response = httpRequest.sendGet(urlMethod.toString());
        return Boolean.valueOf(response);
    }
}
