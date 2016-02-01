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
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 27/08/2015
 */
@Service
public class TimeMapper implements IJsonMappable<TimeBO> {
    
    protected static final Logger LOGGER = LoggerFactory.getLogger(TimeMapper.class);

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
                Map<String, List<TimeBO>> timeMap =
                    JSON_MAPPER.readValue(objectAsJSON, new TypeReference<Map<String, List<TimeBO>>>() { });
                if (timeMap != null && !timeMap.isEmpty()) {
                    timeBOSet = new HashSet(timeMap.values().iterator().next());
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return timeBOSet;
    }
    
}
