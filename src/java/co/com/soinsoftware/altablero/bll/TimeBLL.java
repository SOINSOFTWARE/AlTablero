/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.TimeBO;
import co.com.soinsoftware.altablero.json.mapper.TimeMapper;
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
public class TimeBLL extends AbstractBLL {
    
    @Autowired
    private TimeMapper timeMapper;
    
    public Set<TimeBO> findAll() throws IOException {
        final String method = MODULE_TIME + PATH_ALL;
        final String response = (String) httpRequest.sendGet(method);
        return timeMapper.getObjectSetFromJSON(response);
    }    
}
