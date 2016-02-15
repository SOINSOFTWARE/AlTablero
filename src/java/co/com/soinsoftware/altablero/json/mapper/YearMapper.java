/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.YearBO;
import static co.com.soinsoftware.altablero.json.mapper.IJsonMappable.JSON_MAPPER;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 29/06/2015
 * @version 1.0
 */
@Service
public class YearMapper implements IJsonMappable<YearBO> {

    @Override
    public YearBO getObjectFromJSON(String objectAsJSON) {
        YearBO yearBO = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                yearBO = JSON_MAPPER.readValue(objectAsJSON, YearBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return yearBO;
    }

    @Override
    public Set<YearBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<YearBO> yearBOSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                Map<String, List<YearBO>> yearMap
                        = JSON_MAPPER.readValue(objectAsJSON,
                                new TypeReference<Map<String, List<YearBO>>>() {
                                });
                if (yearMap != null && !yearMap.isEmpty()) {
                    yearBOSet = new HashSet(yearMap.values().iterator().next());
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return yearBOSet;
    }
}
