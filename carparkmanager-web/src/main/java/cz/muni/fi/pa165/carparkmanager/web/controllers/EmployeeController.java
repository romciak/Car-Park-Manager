package cz.muni.fi.pa165.carparkmanager.web.controllers;

import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.EmployeeFacade;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Jakub Juřena <445319>
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    final static Logger log = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private EmployeeFacade employeeFacade;
    
    private String LOGIN_REDIRECT = "redirect:auth/login";

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list (Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        EmployeeDTO authEmployee = (EmployeeDTO) request.getSession().getAttribute("authEmployee");
        if (authEmployee == null) {
            log.warn("Failed. Unautorized");
            redirectAttributes.addFlashAttribute("alert_danger",
                    "Unauthorized.");
            return LOGIN_REDIRECT;
        }
        model.addAttribute("authEmployee", authEmployee);
        model.addAttribute("employees", employeeFacade.findAll());
        return "employees/list";
    }
    
    // TODO create, update, delete
   
}