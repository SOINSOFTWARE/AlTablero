/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.spring.web.controller;

import co.com.carpco.altablero.entity.UserBO;
import co.com.carpco.altablero.utils.RoleUtils;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lina Florez
 */
@Controller
public class TeacherController {

    @Autowired
    private RoleUtils roleUtils;

    @RequestMapping(value = "/admin/profesor", method = RequestMethod.GET)
    public ModelAndView generalInformation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {

            ModelAndView model = null;
            try {
                model = roleUtils.createModelWithUserDetails(auth.getName());
            } catch (IOException ex) {
                Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
            }
            model.setViewName("admin/teacher/edit");
            return model;
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
    
    
  @RequestMapping(value = "/admin/profesor/save", method = RequestMethod.POST)
  public ModelAndView saveInformation(@RequestParam(value = "selDocType", required = true) String docType,
          @RequestParam(value = "txtDocNumber", required = true) String docNumber,
          @RequestParam(value = "txtName", required = true) String name,
          @RequestParam(value = "txtLastName", required = true) String lastName,
          @RequestParam(value = "txtBornDate", required = true) String bornDate,
          @RequestParam(value = "txtAddress", required = true) String address,
          @RequestParam(value = "txtPhone1", required = true) Long phone1,
          @RequestParam(value = "txtPhone2", required = true) Long phone2,
          @RequestParam(value = "selGender", required = true) String gender
  ) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (!(auth instanceof AnonymousAuthenticationToken)) {

     
      ModelAndView model = null;
        try {
            model = roleUtils.createModelWithUserDetails(auth.getName());
        } catch (IOException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
      model.setViewName("admin/teacher/edit");
      UserBO userBo = new UserBO();
      userBo.setDocumentType(docType);
      userBo.setDocumentNumber(docNumber);
      userBo.setName(name);
      userBo.setLastName(lastName);
      userBo.setBorn(new Date());
      userBo.setAddress(address);
      userBo.setPhone1(phone1);
      userBo.setPhone2(phone2);
      userBo.setGender(gender);
      
      return model;
    } else {
      return new ModelAndView("redirect:/login");
    }

  }
}
