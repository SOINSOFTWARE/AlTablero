/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.json.mapper;

import co.com.carpco.altablero.entity.YearBO;
import static co.com.carpco.altablero.json.mapper.IJsonMappable.JSON_MAPPER;
import co.com.carpco.altablero.utils.JsonDateFormat;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 29/06/2015
 */
@Service
public class YearMapper implements IJsonMappable<YearBO> {
    
    protected static final Logger LOGGER = LoggerFactory.getLogger(YearMapper.class);
    
    @Autowired
    private JsonDateFormat dateFormat;

    @Override
    public YearBO getObjectFromJSON(String objectAsJSON) {
        YearBO yearBO = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                yearBO = JSON_MAPPER.readValue(objectAsJSON, YearBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return yearBO;
    }
    
    @Override
    public Set<YearBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<YearBO> yearBOSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                Map<String, List<YearBO>> yearMap =
                    JSON_MAPPER.readValue(objectAsJSON, new TypeReference<Map<String, List<YearBO>>>() { });
                if (yearMap != null && !yearMap.isEmpty()) {
                    yearBOSet = new HashSet(yearMap.values().iterator().next());
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return yearBOSet;
    }
}
