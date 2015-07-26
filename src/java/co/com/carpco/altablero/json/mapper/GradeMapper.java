/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.json.mapper;

import co.com.carpco.altablero.entity.GradeBO;
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
public class GradeMapper implements IJsonMappable<GradeBO> {
    
    protected static final Logger LOGGER = LoggerFactory.getLogger(GradeMapper.class);
    
    @Autowired
    private JsonDateFormat dateFormat;

    @Override
    public GradeBO getObjectFromJSON(String objectAsJSON) {
        GradeBO gradeBO = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                gradeBO = JSON_MAPPER.readValue(objectAsJSON, GradeBO.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return gradeBO;
    }
    
    @Override
    public Set<GradeBO> getObjectSetFromJSON(String objectAsJSON) {
        Set<GradeBO> gradeBOSet = null;
        if (objectAsJSON != null && !objectAsJSON.equals("")) {
            try {
                Map<String, List<GradeBO>> gradeMap =
                    JSON_MAPPER.readValue(objectAsJSON, new TypeReference<Map<String, List<GradeBO>>>() { });
                if (gradeMap != null && !gradeMap.isEmpty()) {
                    gradeBOSet = new HashSet(gradeMap.values().iterator().next());
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return gradeBOSet;
    }
}
