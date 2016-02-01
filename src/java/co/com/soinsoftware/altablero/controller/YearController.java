/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.YearBLL;
import co.com.soinsoftware.altablero.entity.YearBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 */
@Service
public class YearController {

    @Autowired
    private YearBLL yearBLL;

    public YearBO findCurrentYear() throws IOException {
        return yearBLL.findCurrentYear();
    }

    public String getDefaultYear() {
        Calendar date = Calendar.getInstance();
        return String.valueOf(date.get(Calendar.YEAR));
    }

    public List<YearBO> findAll() throws IOException {
        List<YearBO> yearBOList = yearBOList = new ArrayList<>(yearBLL.findAll());
        Collections.sort(yearBOList);
        return yearBOList;
    }
}
