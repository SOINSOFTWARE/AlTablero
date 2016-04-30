/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.NoteValueBO;
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
 * @since 29/04/2016
 * @version 1.0
 */
@Service
public class NoteValueMapper implements IJsonMappable<NoteValueBO> {

    @Override
    public NoteValueBO getObjectFromJSON(String objectAsJSON) {
        NoteValueBO noteValue = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                noteValue = JSON_MAPPER.readValue(objectAsJSON, NoteValueBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return noteValue;
    }

    @Override
    public Set<NoteValueBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<NoteValueBO> noteValueSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                final Map<String, List<NoteValueBO>> noteValueMap
                        = JSON_MAPPER.readValue(objectAsJSON,
                                new TypeReference<Map<String, List<NoteValueBO>>>() {
                                });
                if (noteValueMap != null && !noteValueMap.isEmpty()) {
                    noteValueSet = new HashSet(noteValueMap.values().iterator().next());
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return noteValueSet;
    }
}
