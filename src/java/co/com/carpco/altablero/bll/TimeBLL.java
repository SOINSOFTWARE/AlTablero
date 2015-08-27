/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.bll;

import co.com.carpco.altablero.entity.TimeBO;
import co.com.carpco.altablero.json.mapper.TimeMapper;
import co.com.carpco.altablero.utils.HttpRequest;
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
public class TimeBLL {
    
    private static final String FIND_ALL = "time/all";
    
    @Autowired
    private HttpRequest httpRequest;
    
    @Autowired
    private TimeMapper timeMapper;
    
    public Set<TimeBO> findAll() throws IOException {
        String response = httpRequest.sendGet(FIND_ALL);
        return timeMapper.getObjectSetFromJSON(response);
    }
    
}
