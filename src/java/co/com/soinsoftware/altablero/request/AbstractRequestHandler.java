/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.bll.UserBLL;
import co.com.soinsoftware.altablero.entity.SchoolBO;
import co.com.soinsoftware.altablero.entity.UserBO;
import co.com.soinsoftware.altablero.utils.AuthenticationUtils;
import co.com.soinsoftware.altablero.utils.RoleUtils;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Carlos Rodriguez
 */
public abstract class AbstractRequestHandler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractRequestHandler.class);
    
    protected static final String HAS_SERVER_ERRORS_PARAMETER = "hasServerErrors";

    @Autowired
    private RoleUtils roleUtils;

    @Autowired
    private UserBLL userBLL;

    protected ModelAndView buildModelAndView() throws IOException {
        final String documentNumber = AuthenticationUtils.getDocumentNumberFromAuthentication();
        final ModelAndView model = this.roleUtils.createModelWithUserDetails(documentNumber);
        return model;
    }

    protected UserBO getLogeduser() {
        final String documentNumber = AuthenticationUtils.getDocumentNumberFromAuthentication();
        UserBO user = null;
        try {
            user = this.userBLL.findUserByDocument(documentNumber);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
        return user;
    }

    protected int getIdSchool() {
        final UserBO user = this.getLogeduser();
        final SchoolBO school = (user != null) ? user.getSchool() : null;
        return (school != null) ? school.getId() : 0;
    }
}
