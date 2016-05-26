/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.UserBO;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 31/03/2015
 * @version 1.0
 */
@Service
public class UserMapper implements IJsonMappable<UserBO> {

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
                final List<UserBO> userList = JSON_MAPPER.readValue(
                        objectAsJSON, new TypeReference<List<UserBO>>() {});
                if (userList != null && !userList.isEmpty()) {
                    userSet = new HashSet(userList);
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return userSet;
    }
}
