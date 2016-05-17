/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.YearBO;
import co.com.soinsoftware.altablero.json.mapper.YearMapper;
import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Rodriguez
 * @since 29/06/2015
 * @version 1.0
 */
@Service
public class YearBLL extends AbstractBLL {

    @Autowired
    private YearMapper yearMapper;

    public Set<YearBO> findAll() throws IOException {
        final String method = MODULE_YEAR + PATH_ALL;
        final String response = (String) httpRequest.sendGet(method);
        return yearMapper.getObjectSetFromJSON(response);
    }

    public YearBO findCurrentYear() throws IOException {
        final String method = MODULE_YEAR + PATH_CURRENT_YEAR;
        final String response = (String) httpRequest.sendGet(method);
        return yearMapper.getObjectFromJSON(response);
    }
}
