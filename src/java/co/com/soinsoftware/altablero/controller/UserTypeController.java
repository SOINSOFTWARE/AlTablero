/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.UserTypeBLL;
import co.com.soinsoftware.altablero.entity.UserTypeBO;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 26/02/2016
 */
@Service
public class UserTypeController {

    @Autowired
    private UserTypeBLL userTypeBLL;

    public UserTypeBO findBy(final String code) throws IOException {
        return this.userTypeBLL.findBy(code);
    }
}
