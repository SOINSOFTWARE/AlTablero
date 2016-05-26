/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.PeriodBO;
import static co.com.soinsoftware.altablero.json.mapper.IJsonMappable.JSON_MAPPER;
import static co.com.soinsoftware.altablero.json.mapper.IJsonMappable.LOGGER;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 21/04/2016
 * @version 1.0
 */
@Service
public class PeriodMapper implements IJsonMappable<PeriodBO> {

    @Override
    public PeriodBO getObjectFromJSON(String objectAsJSON) {
        PeriodBO period = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                period = JSON_MAPPER.readValue(objectAsJSON, PeriodBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return period;
    }

    @Override
    public Set<PeriodBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<PeriodBO> periodSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                final List<PeriodBO> periodList = JSON_MAPPER.readValue(
                        objectAsJSON, new TypeReference<List<PeriodBO>>() { });
                if (periodList != null && !periodList.isEmpty()) {
                    periodSet = new HashSet(periodList);
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return periodSet;
    }
}
