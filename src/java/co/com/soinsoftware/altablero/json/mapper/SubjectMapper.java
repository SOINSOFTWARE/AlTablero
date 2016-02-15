/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.SubjectBO;
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
 * @since 03/02/2016
 * @version 1.0
 */
@Service
public class SubjectMapper implements IJsonMappable<SubjectBO> {

    @Override
    public SubjectBO getObjectFromJSON(String objectAsJSON) {
        SubjectBO subjectBO = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                subjectBO = JSON_MAPPER.readValue(objectAsJSON, SubjectBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return subjectBO;
    }

    @Override
    public Set<SubjectBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<SubjectBO> subjectBOSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                Map<String, List<SubjectBO>> subjectMap
                        = JSON_MAPPER.readValue(objectAsJSON,
                                new TypeReference<Map<String, List<SubjectBO>>>() {
                                });
                if (subjectMap != null && !subjectMap.isEmpty()) {
                    subjectBOSet = new HashSet(subjectMap.values().iterator().next());
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return subjectBOSet;
    }
}
