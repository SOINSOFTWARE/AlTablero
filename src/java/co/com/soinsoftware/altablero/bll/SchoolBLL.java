/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.SchoolBO;
import co.com.soinsoftware.altablero.json.mapper.SchoolMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 25/02/2016
 */
@Service
public class SchoolBLL extends AbstractBLL {
    
    @Autowired
    private SchoolMapper schoolMapper;
    
    public SchoolBO findByIdentifier(final int identifier) throws IOException {
        final StringBuilder method = new StringBuilder(MODULE_SCHOOL + PATH_BY);
        method.append(this.buildRequestParameter(ADD_PARAMETERS, PARAMETER_SCHOOL_ID, identifier));
        final String response = httpRequest.sendGet(method.toString());
        return schoolMapper.getObjectFromJSON(response);
    }
}
