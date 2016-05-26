/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.TimeBO;
import static co.com.soinsoftware.altablero.json.mapper.IJsonMappable.JSON_MAPPER;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 27/08/2015
 * @version 1.0
 */
@Service
public class TimeMapper implements IJsonMappable<TimeBO> {

    @Override
    public TimeBO getObjectFromJSON(String objectAsJSON) {
        TimeBO timeBO = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                timeBO = JSON_MAPPER.readValue(objectAsJSON, TimeBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return timeBO;
    }

    @Override
    public Set<TimeBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<TimeBO> timeBOSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                final List<TimeBO> timeList = JSON_MAPPER.readValue(
                        objectAsJSON, new TypeReference<List<TimeBO>>() { });
                if (timeList != null && !timeList.isEmpty()) {
                    timeBOSet = new HashSet(timeList);
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return timeBOSet;
    }
}
