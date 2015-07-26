/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.carpco.altablero.spring.web.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author Carlos
 */
@Controller
public class MainBoardController extends AbstractController {
    
    private static final String DEFAULT_PAGE = "/";    
    private static final String GENERAL_PAGE = "/admin/general";    
    //private static final Logger LOGGER = LoggerFactory.getLogger(MainBoardController.class);
    
    @RequestMapping(value = { DEFAULT_PAGE, GENERAL_PAGE }, method = RequestMethod.GET)
    public ModelAndView generalPage() {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(GENERAL_PAGE);
        } catch (IOException ex) {
            //LOGGER.error(ex.getMessage());
            model = LoginController.buildRedirectLoginModel();
        }
        return model;
    }
}
