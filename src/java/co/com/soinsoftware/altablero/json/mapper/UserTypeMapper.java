/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.UserTypeBO;
import static co.com.soinsoftware.altablero.json.mapper.IJsonMappable.JSON_MAPPER;
import static co.com.soinsoftware.altablero.json.mapper.IJsonMappable.LOGGER;
import java.io.IOException;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 26/02/2016
 */
@Service
public class UserTypeMapper implements IJsonMappable<UserTypeBO>{

    @Override
    public UserTypeBO getObjectFromJSON(String objectAsJSON) {
        UserTypeBO userType = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                userType = JSON_MAPPER.readValue(objectAsJSON, UserTypeBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return userType;
    }

    @Override
    public Set<UserTypeBO> getObjectSetFromJSON(String objectAsJSON) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
}
