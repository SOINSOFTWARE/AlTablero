/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.SubjectBO;
import co.com.soinsoftware.altablero.json.mapper.SubjectMapper;
import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 03/02/2015
 * @version 1.0
 */
@Service
public class SubjectBLL extends AbstractBLL {

    @Autowired
    private SubjectMapper subjectMapper;

    public Set<SubjectBO> findExcludingClass(final int idClassRoom)
            throws IOException {
        final StringBuilder builder = new StringBuilder(
                MODULE_SUBJECT + PATH_EXCLUDING_CLASS);
        builder.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_CLASSROOM_ID,
                idClassRoom));
        final String response = (String) httpRequest.sendGet(builder.toString());
        return subjectMapper.getObjectSetFromJSON(response);
    }
}
