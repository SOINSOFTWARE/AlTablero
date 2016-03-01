package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.utils.AuthenticationUtils;
import co.com.soinsoftware.altablero.utils.RoleUtils;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlos Rodriguez
 */
@Controller
public class LoginRequestHandler extends AbstractRequestHandler {

    private static final String LOCKSCREEN_PAGE = "/lockscreen";
    private static final String LOGIN_PAGE = "/login";
    private static final String NOT_FOUND_PAGE = "/403";
    private static final String REDIRECT_LOGIN_PAGE = "redirect:/login";

    private static final String ERROR_PARAMETER = "error";
    private static final String LOGOUT_PARAMETER = "logout";
    private static final String MESSAGE_PARAMETER = "msg";

    private static final String ERROR_MESSAGE = "¡Número de documento o contraseña invalida!";
    private static final String LOGOUT_MESSAGE = "La sesión ha sido cerrada correctamente.";

    @Autowired
    RoleUtils roleUtils;

    @RequestMapping(value = LOGIN_PAGE, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = ERROR_PARAMETER, required = false) String error,
            @RequestParam(value = LOGOUT_PARAMETER, required = false) String logout) {

        final ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject(ERROR_PARAMETER, ERROR_MESSAGE);
        }

        if (logout != null) {
            model.addObject(MESSAGE_PARAMETER, LOGOUT_MESSAGE);
            AuthenticationUtils.setAnonymusAuthentication();
        }
        model.setViewName(LOGIN_PAGE);
        return model;
    }

    @RequestMapping(value = LOCKSCREEN_PAGE, method = RequestMethod.GET)
    public ModelAndView loockscreen() {
        return AuthenticationUtils.isAnonymusAuthentication()
                ? LoginRequestHandler.buildRedirectLoginModel()
                : this.buildLockScreenModel();
    }

    @RequestMapping(value = NOT_FOUND_PAGE, method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        return this.buildNotFoundModel();
    }

    public ModelAndView buildLockScreenModel() {
        ModelAndView model;
        final String documentNumber
                = AuthenticationUtils.getDocumentNumberFromAuthentication();
        try {
            model = roleUtils.createModelWithUserDetails(documentNumber, 0);
            model.setViewName(LOCKSCREEN_PAGE);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        AuthenticationUtils.setAnonymusAuthentication();
        return model;
    }

    public ModelAndView buildNotFoundModel() {
        ModelAndView model = LoginRequestHandler.buildRedirectLoginModel();
        if (!AuthenticationUtils.isAnonymusAuthentication()) {
            final String documentNumber
                    = AuthenticationUtils.getDocumentNumberFromAuthentication();
            try {
                model = roleUtils.createModelWithUserDetails(documentNumber, 0);
                model.setViewName(NOT_FOUND_PAGE);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return model;
    }

    public static ModelAndView buildRedirectLoginModel() {
        return new ModelAndView(REDIRECT_LOGIN_PAGE);
    }
}
