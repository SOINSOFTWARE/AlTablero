package co.com.carpco.altablero.spring.web.controller;

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

    @RequestMapping(value = "/admin/admin" , method = RequestMethod.GET)
    public ModelAndView adminPage() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            ModelAndView model = new ModelAndView();
            model.addObject("title", "Spring Security Login Form - Database Authentication");
            model.addObject("message", "This page is for ROLE_ADMIN only!");
            model.setViewName("admin/admin");

            return model;
        } else 
        {
            return this.login(null, null);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, 
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
        }
        model.setViewName("login");

        return model;
    }
    
    @RequestMapping(value = "/admin/lockscreen", method = RequestMethod.GET)
    public ModelAndView loockscreen() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            ModelAndView model = new ModelAndView();
            model.addObject("username", "Carlos Rodriguez");
            model.addObject("avatar", "avatar5");
            model.setViewName("admin/lockscreen");

            return model;
        } else 
        {
            return this.login(null, null);
        }
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
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