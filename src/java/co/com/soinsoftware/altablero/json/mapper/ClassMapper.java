/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.ClassBO;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 05/02/2016
 * @version 1.0
 */
@Service
public class ClassMapper implements IJsonMappable<ClassBO> {

    @Override
    public ClassBO getObjectFromJSON(String objectAsJSON) {
        ClassBO classBO = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                classBO = JSON_MAPPER.readValue(objectAsJSON, ClassBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return classBO;
    }

    @Override
    public Set<ClassBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<ClassBO> classBOSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                Map<String, List<ClassBO>> classMap
                        = JSON_MAPPER.readValue(objectAsJSON,
                                new TypeReference<Map<String, List<ClassBO>>>() {
                                });
                if (classMap != null && !classMap.isEmpty()) {
                    classBOSet = new HashSet(classMap.values().iterator().next());
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return classBOSet;
    }
}
