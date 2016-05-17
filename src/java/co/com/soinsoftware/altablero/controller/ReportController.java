/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.controller;

import co.com.soinsoftware.altablero.bll.ReportBLL;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 17/05/2016
 * @version 1.0
 */
@Service
public class ReportController {
    
    @Autowired
    private ReportBLL reportBLL;
    
    public InputStream generateReports(final int idSchool, final int idClassRoom,
            final int idPeriod) throws IOException {
        return this.reportBLL.generateReports(idSchool, idClassRoom, idPeriod);
    }    
}
