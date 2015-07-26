/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.json.mapper;

import co.com.carpco.altablero.entity.SchoolBO;
import java.io.IOException;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 17/03/2015
 */
@Service
public class SchoolMapper implements IJsonMappable<SchoolBO> {
    
    protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolMapper.class);

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
