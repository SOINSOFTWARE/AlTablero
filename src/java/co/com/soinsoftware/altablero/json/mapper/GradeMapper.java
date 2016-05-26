/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.json.mapper;

import co.com.soinsoftware.altablero.entity.GradeBO;
import static co.com.soinsoftware.altablero.json.mapper.IJsonMappable.JSON_MAPPER;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 29/06/2015
 * @version 1.0
 */
@Service
public class GradeMapper implements IJsonMappable<GradeBO> {

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
                final List<GradeBO> gradeList = JSON_MAPPER.readValue(
                        objectAsJSON, new TypeReference<List<GradeBO>>() { });
                if (gradeList != null && !gradeList.isEmpty()) {
                    gradeBOSet = new HashSet(gradeList);
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return gradeBOSet;
    }
}
