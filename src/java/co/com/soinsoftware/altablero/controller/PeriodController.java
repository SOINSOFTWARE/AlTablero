/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.PeriodBLL;
import co.com.soinsoftware.altablero.entity.PeriodBO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 21/04/2016
 * @version 1.0
 */
@Service
public class PeriodController {
    
    @Autowired
    private PeriodBLL bll;
    
    public List<PeriodBO> findAll(final int idSchool) throws IOException {
        final Set<PeriodBO> periodSet = this.bll.findAll(idSchool);
        final List<PeriodBO> periodList = (periodSet != null)
                ? new ArrayList<>(periodSet) : new ArrayList<>();
        Collections.sort(periodList);
        return periodList;
    }
}
