/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soinsoftware.altablero.request;

import co.com.soinsoftware.altablero.entity.UserBO;
import static co.com.soinsoftware.altablero.request.AbstractRequestHandler.LOGGER;
import java.io.IOException;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Lina Florez
 */
@Controller
public class TeacherRequestHandler extends AbstractRequestHandler {

    private static final String EDIT_PAGE = "admin/teacher/edit";

    @RequestMapping(value = "/admin/profesor", method = RequestMethod.GET)
    public ModelAndView generalInformation() {
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(EDIT_PAGE);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
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
        ModelAndView model = null;
        try {
            model = this.buildModelAndView();
            model.setViewName(EDIT_PAGE);
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
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            model = LoginRequestHandler.buildRedirectLoginModel();
        }
        return model;
    }
}
