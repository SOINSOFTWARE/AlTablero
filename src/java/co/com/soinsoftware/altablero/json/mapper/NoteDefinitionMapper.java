/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.NoteDefinitionBO;
import static co.com.soinsoftware.altablero.json.mapper.IJsonMappable.JSON_MAPPER;
import static co.com.soinsoftware.altablero.json.mapper.IJsonMappable.LOGGER;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 25/04/2016
 * @version 1.0
 */
@Service
public class NoteDefinitionMapper implements IJsonMappable<NoteDefinitionBO> {

    @Override
    public NoteDefinitionBO getObjectFromJSON(String objectAsJSON) {
        NoteDefinitionBO noteDefinition = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                noteDefinition = JSON_MAPPER.readValue(objectAsJSON, NoteDefinitionBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return noteDefinition;
    }

    @Override
    public Set<NoteDefinitionBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<NoteDefinitionBO> noteDefSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                final Map<String, List<NoteDefinitionBO>> noteDefMap
                        = JSON_MAPPER.readValue(objectAsJSON,
                                new TypeReference<Map<String, List<NoteDefinitionBO>>>() {
                                });
                if (noteDefMap != null && !noteDefMap.isEmpty()) {
                    noteDefSet = new HashSet(noteDefMap.values().iterator().next());
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return noteDefSet;
    }
}
