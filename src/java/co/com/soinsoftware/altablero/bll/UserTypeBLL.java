/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import co.com.soinsoftware.altablero.entity.UserTypeBO;
import co.com.soinsoftware.altablero.json.mapper.UserTypeMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 26/02/2016
 */
@Service
public class UserTypeBLL extends AbstractBLL {

    @Autowired
    private UserTypeMapper userTypeMapper;

    public UserTypeBO findBy(final String code) throws IOException {
        final StringBuilder method = new StringBuilder(MODULE_USERTYPE + PATH_BY);
        method.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_CODE, code));
        final String response = (String) httpRequest.sendGet(method.toString());
        return userTypeMapper.getObjectFromJSON(response);
    }
}
