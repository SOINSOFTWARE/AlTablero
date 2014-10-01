package co.com.carpco.altablero.spring.web.controller;

import co.com.carpco.altablero.hibernate.bll.BzUserBll;
import co.com.carpco.altablero.hibernate.entities.BzUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    
    @Autowired
    BzUserBll bzUserBO;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, 
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "¡Número de documento o contraseña invalida!");
        }

        if (logout != null) {
            model.addObject("msg", "La sesión ha sido cerrada correctamente.");
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
        }
        model.setViewName("login");

        return model;
    }
    
    @RequestMapping(value = "/lockscreen", method = RequestMethod.GET)
    public ModelAndView loockscreen() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            BzUser bzUser = bzUserBO.getUserByDocumentNumber(auth.getName());
            
            ModelAndView model = new ModelAndView();
            model.addObject("user", bzUser.getName() + " " + bzUser.getLastName());
            model.addObject("username", bzUser.getDocumentNumber());
            
            if (bzUser.getGender().equals("Masculino")) {
                model.addObject("avatar", "avatar5");
            } else {
                model.addObject("avatar", "avatar2");
            }
            
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
            model.setViewName("lockscreen");
            return model;
        } else 
        {
            return this.login(null, null);
        }
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }
        model.setViewName("403");
        return model;
    }
}