/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.json.mapper;

import co.com.carpco.altablero.entity.UserBO;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 31/03/2015
 */
@Service
public class UserMapper implements IJsonMappable<UserBO> {

    /**
     * Logger object
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

    @Override
    public UserBO getObjectFromJSON(String objectAsJSON) {
        UserBO user = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                user = JSON_MAPPER.readValue(objectAsJSON, UserBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return user;
    }

    @Override
    public Set<UserBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<UserBO> userSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                Map<String, List<UserBO>> userMap =
                    JSON_MAPPER.readValue(objectAsJSON, new TypeReference<Map<String, List<UserBO>>>() { });
                if (userMap != null && !userMap.isEmpty()) {
                    userSet = new HashSet(userMap.values().iterator().next());
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return userSet;
    }
}