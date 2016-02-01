/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.TimeBLL;
import co.com.soinsoftware.altablero.entity.TimeBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 */
@Service
public class TimeController {

    @Autowired
    private TimeBLL timeBLL;

    public List<TimeBO> findAll() throws IOException {
        List<TimeBO> timeBOList = timeBOList = new ArrayList<>(timeBLL.findAll());
        Collections.sort(timeBOList);
        return timeBOList;
    }
}
