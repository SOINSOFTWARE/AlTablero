/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.GradeBO;
import co.com.soinsoftware.altablero.entity.YearBO;
import co.com.soinsoftware.altablero.json.mapper.GradeMapper;
import co.com.soinsoftware.altablero.json.mapper.YearMapper;
import co.com.soinsoftware.altablero.utils.HttpRequest;
import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @since 29/06/2015
 */
@Service
public class GradeBLL {
    
    private static final String FIND_ALL = "grade/all";
    
    @Autowired
    private HttpRequest httpRequest;
    
    @Autowired
    private GradeMapper gradeMapper;
    
    public Set<GradeBO> findAll() throws IOException {
        String response = httpRequest.sendGet(FIND_ALL);
        return gradeMapper.getObjectSetFromJSON(response);
    }
}
