/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.GradeBO;
import co.com.soinsoftware.altablero.json.mapper.GradeMapper;
import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 29/06/2015
 * @version 1.0
 */
@Service
public class GradeBLL extends AbstractBLL {
    
    @Autowired
    private GradeMapper gradeMapper;
    
    public Set<GradeBO> findAll() throws IOException {
        final String method = MODULE_GRADE + PATH_ALL;
        final String response = httpRequest.sendGet(method);
        return gradeMapper.getObjectSetFromJSON(response);
    }
}
