/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.SchoolBLL;
import co.com.soinsoftware.altablero.entity.SchoolBO;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 25/02/2016
 */
@Service
public class SchoolController {

    @Autowired
    private SchoolBLL schoolBLL;

    public SchoolBO findByIdentifier(final int identifier) throws IOException {
        return schoolBLL.findByIdentifier(identifier);
    }
}
