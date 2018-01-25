package cz.muni.fi.pa165.carparkmanager.web.controllers;

import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.EmployeeFacade;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author xbonco1
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeFacade employeeFacade;
    
    private String LOGIN_REDIRECT = "redirect:/auth/login";
    private String EMPLOYEE_LIST_REDIRECT = "redirect:/employees/list";

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
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
                         HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {

        EmployeeDTO authEmployee = (EmployeeDTO) request.getSession().getAttribute("authEmployee");
        if (authEmployee == null || !authEmployee.isAdmin()) {
            log.warn("Failed. Unauthorized");
            redirectAttributes.addFlashAttribute("alert_danger",
                    "Unauthorized.");
            return LOGIN_REDIRECT;
        }

        EmployeeDTO employee = employeeFacade.findById(id);
        if(employee.isAdmin()){
            redirectAttributes.addFlashAttribute("alert_danger", "Employee \"" + employee + "\" is admin " +
                    "and cannot be deleted.");
            return EMPLOYEE_LIST_REDIRECT;
        }
        
        model.addAttribute("authenticatedUser", authEmployee);
        employeeFacade.delete(employee);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Employee \"" + employee.getEmail() + "\" was deleted.");
        return EMPLOYEE_LIST_REDIRECT;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("employeeCreate") EmployeeDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder,
                         HttpServletRequest request) {

        EmployeeDTO authEmployee = (EmployeeDTO) request.getSession().getAttribute("authEmployee");
        if (authEmployee == null || !authEmployee.isAdmin()) {
            log.warn("Failed. Unauthorized");
            redirectAttributes.addFlashAttribute("alert_danger",
                    "Unauthorized.");
            return LOGIN_REDIRECT;
        }

        log.debug("create(employeeCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return EMPLOYEE_LIST_REDIRECT;
        }
        //create employee
        employeeFacade.create(formBean);
        
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Employee was created");
        return EMPLOYEE_LIST_REDIRECT;
    }  
}