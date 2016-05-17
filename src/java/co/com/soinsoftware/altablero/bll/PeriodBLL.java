/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.PeriodBO;
import co.com.soinsoftware.altablero.json.mapper.PeriodMapper;
import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 21/04/2016
 * @version 1.0
 */
@Service
public class PeriodBLL extends AbstractBLL {

    @Autowired
    private PeriodMapper mapper;

    public Set<PeriodBO> findAll(final int idSchool) throws IOException {
        final StringBuilder methodBuilder = new StringBuilder();
        methodBuilder.append(MODULE_PERIOD + PATH_ALL);
        methodBuilder.append(
                this.buildRequestParameter(ADD_PARAMETERS, PARAMETER_SCHOOL_ID, idSchool));
        final String response = (String) httpRequest.sendGet(methodBuilder.toString());
        return this.mapper.getObjectSetFromJSON(response);
    }
}
