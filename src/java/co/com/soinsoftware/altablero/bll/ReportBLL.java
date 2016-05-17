/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.bll;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Rodriguez
 * @since 17/05/2016
 * @version 1.0
 */
@Service
public class ReportBLL extends AbstractBLL {

    public InputStream generateReports(final int idSchool, final int idClassRoom,
            final int idPeriod) throws IOException {
        final StringBuilder builder = new StringBuilder(MODULE_REPORT + PATH_BY);
        builder.append(buildRequestParameter(ADD_PARAMETERS, PARAMETER_CLASSROOM_ID,
                idClassRoom));
        builder.append(buildRequestParameter(CONCAT, PARAMETER_SCHOOL_ID, idSchool));
        builder.append(buildRequestParameter(CONCAT, PARAMETER_PERIOD_ID, idPeriod));
        return (InputStream) httpRequest.sendGet(builder.toString());
    }
}
