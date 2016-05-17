/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.ClassBO;
import co.com.soinsoftware.altablero.entity.NoteDefinitionBO;
import co.com.soinsoftware.altablero.entity.NoteValueBO;
import co.com.soinsoftware.altablero.json.mapper.ClassMapper;
import co.com.soinsoftware.altablero.json.mapper.NoteDefinitionMapper;
import co.com.soinsoftware.altablero.json.mapper.NoteValueMapper;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 05/02/2016
 * @version 1.0
 */
@Service
public class ClassBLL extends AbstractBLL {

    @Autowired
    private ClassMapper classMapper;
    
    @Autowired
    private NoteDefinitionMapper noteDefMapper;

    public Set<ClassBO> findClasses(final int idSchool, final int idClassRoom,
            final int idTeacher) throws IOException {
        final StringBuilder builder = new StringBuilder(
                MODULE_CLASS + PATH_BY);
        builder.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_CLASSROOM_ID,
                idClassRoom));
        builder.append(buildRequestParameter(CONCAT, PARAMETER_SCHOOL_ID, idSchool));
        builder.append(buildRequestParameter(CONCAT, PARAMETER_USER_ID, idTeacher));
        final String response = (String) httpRequest.sendGet(builder.toString());
        return classMapper.getObjectSetFromJSON(response);
    }

    public Set<ClassBO> saveClasses(final List<ClassBO> classList)
            throws IOException {
        final String jsonObject = this.writeValueAsString(classList);
        final String method = MODULE_CLASS + PATH_SAVE;
        final String response = (String) httpRequest.sendPost(method, jsonObject);
        return classMapper.getObjectSetFromJSON(response);
    }
    
    public Set<NoteDefinitionBO> findNoteDefinitionByClass(final int idClass,
            final int idPeriod) throws IOException {
        final StringBuilder builder = new StringBuilder(
                MODULE_CLASS + PATH_NOTEDEFINITION_BY_CLASS);
        builder.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_CLASS_ID,
                idClass));
        builder.append(buildRequestParameter(CONCAT, PARAMETER_PERIOD_ID, idPeriod));
        final String response = (String) httpRequest.sendGet(builder.toString());
        return noteDefMapper.getObjectSetFromJSON(response);
    }
    
    public ClassBO saveNoteDefinitionByClass(final List<NoteDefinitionBO> noteDefList)
            throws IOException {
        final String jsonObject = this.writeNoteDefinitionAsString(noteDefList);
        final String method = MODULE_CLASS + PATH_SAVE_NOTEDEFINITION_BY_CLASS;
        final String response = (String) httpRequest.sendPost(method, jsonObject);
        return this.classMapper.getObjectFromJSON(response);
    }
    
    public ClassBO saveNoteValue(final List<NoteValueBO> noteValueList)
            throws IOException {
        final String jsonObject = this.writeNoteValueAsString(noteValueList);
        final String method = MODULE_CLASS + PATH_SAVE_NOTEVALUE;
        final String response = (String) httpRequest.sendPost(method, jsonObject);
        return this.classMapper.getObjectFromJSON(response);
    }

    private String writeValueAsString(final List<ClassBO> classList) {
        String jsonObject = null;
        try {
            jsonObject = ClassMapper.JSON_WRITER.writeValueAsString(classList);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return jsonObject;
    }
    
    private String writeNoteDefinitionAsString(final List<NoteDefinitionBO> noteDefList) {
        String jsonObject = null;
        try {
            jsonObject = NoteDefinitionMapper.JSON_WRITER.writeValueAsString(noteDefList);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return jsonObject;
    }
    
    private String writeNoteValueAsString(final List<NoteValueBO> noteDefList) {
        String jsonObject = null;
        try {
            jsonObject = NoteValueMapper.JSON_WRITER.writeValueAsString(noteDefList);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return jsonObject;
    }
}
