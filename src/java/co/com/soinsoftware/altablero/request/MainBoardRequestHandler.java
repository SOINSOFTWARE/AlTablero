/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.entity.UserBO;
import static co.com.soinsoftware.altablero.request.AbstractRequestHandler.USER_PARAMETER;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlos Rodriguez
 */
@Controller
public class MainBoardRequestHandler extends AbstractRequestHandler {

    private static final String DEFAULT_PAGE = "/";
    private static final String GENERAL_PAGE = "/admin/general";
    private static final String PASSWORD_PAGE = "/contrasena";
    private static final String REDIRECT_PASSWORD_PAGE = "redirect:/contrasena";
    private static final String REDIRECT_GENERAL_PAGE = "redirect:/admin/general";

    private static final String PASSWORD_MODEL = "/password_change";

    private static final String DEFAULT_PASSWORD = "123456";

    private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_CURRENT_PARAMETER = "currentPassword";
    private static final String PASSWORD_NEW1_PARAMETER = "newPassword1";
    private static final String PASSWORD_NEW2_PARAMETER = "newPassword2";

    @RequestMapping(value = {DEFAULT_PAGE, GENERAL_PAGE}, method = RequestMethod.GET)
    public ModelAndView generalPage() {
        ModelAndView model = null;
        try {
            final UserBO logedUser = this.getLogeduser();
            if (logedUser != null && logedUser.getPassword().equals(DEFAULT_PASSWORD)) {
                model = new ModelAndView();
                model.setViewName(REDIRECT_PASSWORD_PAGE);
                model.addObject(USER_PARAMETER, logedUser);
            } else {
                model = this.buildModelAndView(logedUser);
                model.setViewName(GENERAL_PAGE);
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }

    @RequestMapping(value = PASSWORD_PAGE, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView setPassword(
            @RequestParam(value = USERNAME_PARAMETER, required = false) String docNumber,
            @RequestParam(value = PASSWORD_CURRENT_PARAMETER, required = false) String currentPassword,
            @RequestParam(value = PASSWORD_NEW1_PARAMETER, required = false) String newPassword1,
            @RequestParam(value = PASSWORD_NEW2_PARAMETER, required = false) String newPassword2) {
        final ModelAndView model = new ModelAndView();
        final UserBO logedUser = this.getLogeduser();
        if (currentPassword == null && newPassword1 == null && newPassword2 == null) {
            model.setViewName(PASSWORD_MODEL);
            if (logedUser != null) {
                model.addObject(USER_PARAMETER, logedUser);
            }
        } else {
            logedUser.setPassword(newPassword1);
            try {
                final UserBO user = this.userController.save(logedUser);
                if (user == null) {
                    model.setViewName(PASSWORD_MODEL);
                } else {
                    model.setViewName(REDIRECT_GENERAL_PAGE);
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
                model.setViewName(REDIRECT_GENERAL_PAGE);
            }
        }
        return model;
    }
}
