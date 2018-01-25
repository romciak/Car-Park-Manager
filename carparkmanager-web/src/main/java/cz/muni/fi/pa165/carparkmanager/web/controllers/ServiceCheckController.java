package cz.muni.fi.pa165.carparkmanager.web.controllers;

import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.ServiceCheckFacade;
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
@RequestMapping("/service-checks")
public class ServiceCheckController {
    
    final static Logger log = LoggerFactory.getLogger(ServiceCheckController.class);

    @Autowired
    private ServiceCheckFacade serviceCheckFacade;
    
    private String LOGIN_REDIRECT = "redirect:/auth/login";
    private String SERVICECHECKS_LIST_REDIRECT = "redirect:/service-checks/list";

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
        model.addAttribute("serviceChecks", serviceCheckFacade.findAll());
        return "service-checks/list";
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

        ServiceCheckDTO service = serviceCheckFacade.findById(id);
        
        model.addAttribute("authenticatedUser", authEmployee);
        serviceCheckFacade.delete(service);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Service Check \"" + service.toString() + "\" was deleted.");
        return SERVICECHECKS_LIST_REDIRECT;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("employeeCreate") ServiceCheckDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder,
                         HttpServletRequest request) {

        EmployeeDTO authEmployee = (EmployeeDTO) request.getSession().getAttribute("authEmployee");
        if (authEmployee == null || !authEmployee.isAdmin()) {
            log.warn("Failed. Unauthorized");
            redirectAttributes.addFlashAttribute("alert_danger",
                    "Unauthorized.");
            return LOGIN_REDIRECT;
        }

        log.debug("create(serviceCheckCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return SERVICECHECKS_LIST_REDIRECT;
        }
        //create service check
        serviceCheckFacade.create(formBean);
        
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "ServiceCheck was created");
        return SERVICECHECKS_LIST_REDIRECT;
    }   
}