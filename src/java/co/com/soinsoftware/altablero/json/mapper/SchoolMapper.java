/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.SchoolBO;
import java.io.IOException;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @since 17/03/2015
 * @version 1.0
 */
@Service
public class SchoolMapper implements IJsonMappable<SchoolBO> {

    @Override
    public SchoolBO getObjectFromJSON(String objectAsJSON) {
        SchoolBO school = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                school = JSON_MAPPER.readValue(objectAsJSON, SchoolBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return school;
    }

    @Override
    public Set<SchoolBO> getObjectSetFromJSON(String objectAsJSON) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
