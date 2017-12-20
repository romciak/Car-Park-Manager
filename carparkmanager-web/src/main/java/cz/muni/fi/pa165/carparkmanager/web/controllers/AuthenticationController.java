package cz.muni.fi.pa165.carparkmanager.web.controllers;


import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeAuthenticateDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.EmployeeFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jakub Juřena <445319>
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    final static Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private EmployeeFacade employeeFacade;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String authForm(
            Model model,
            HttpServletRequest req,
            HttpServletResponse res) {
        log.info("GET request: /auth/login");
        if (req.getSession().getAttribute("authUser") != null) {
            return "redirect:/";
        }
        return "auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticate(
            @RequestParam String email,
            @RequestParam String password,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {
        log.info("POST request: /auth/login");
        EmployeeAuthenticateDTO employeeAuthenticateDTO = new EmployeeAuthenticateDTO(email, password);

        EmployeeDTO employeeDTO = employeeFacade.authenticate(employeeAuthenticateDTO);
        
        if (employeeDTO == null) {
            log.warn("POST request: /auth/login; wrong login information, entered mail={}", email);
            redirectAttributes.addFlashAttribute(
                    "alert_danger", "Wrong mail or password of user");
            return "redirect:/auth/login";
        }

        employeeDTO.setClassification(employeeFacade.findById(employeeDTO.getId()).getClassification());

        HttpSession session = req.getSession(true);
        session.setAttribute("authEmloyee", employeeDTO);
        log.info("POST request: /auth/login; user with id {} has been logged in", employeeDTO.getId());
        redirectAttributes.addFlashAttribute("alert_info",
                "User with name " + employeeDTO.getSurname() + " " + employeeDTO.getFirstname() + " has been logged in.");
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req) {
        log.info("GET request: /logout");
        HttpSession session = req.getSession(true);
        session.removeAttribute("authEmployee");
        redirectAttributes.addFlashAttribute("alert_info", "You have been successfully logged out.");
        return "redirect:/auth/login";
    }
}
