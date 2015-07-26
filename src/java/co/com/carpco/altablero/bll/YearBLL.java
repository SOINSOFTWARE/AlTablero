/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.bll;

import co.com.carpco.altablero.entity.YearBO;
import co.com.carpco.altablero.json.mapper.YearMapper;
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
public class YearBLL {
    
    private static final String FIND_ALL = "year/all";
    
    @Autowired
    private HttpRequest httpRequest;
    
    @Autowired
    private YearMapper yearMapper;
    
    public Set<YearBO> findAll() throws IOException {
        String response = httpRequest.sendGet(FIND_ALL);
        return yearMapper.getObjectSetFromJSON(response);
    }
}
