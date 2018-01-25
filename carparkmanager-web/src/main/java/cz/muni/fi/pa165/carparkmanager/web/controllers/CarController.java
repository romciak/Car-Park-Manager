package cz.muni.fi.pa165.carparkmanager.web.controllers;

import cz.muni.fi.pa165.carparkmanager.api.dto.CarCreateDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.CarDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.DriveDTO;
import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;

import cz.muni.fi.pa165.carparkmanager.api.facade.CarFacade;
import cz.muni.fi.pa165.carparkmanager.api.facade.DriveFacade;
import java.util.List;
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
 * @author Jakub Juřena <445319>
 */


@Controller
@RequestMapping("/cars")
public class CarController {
    final static Logger log = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarFacade carFacade;
    
    @Autowired
    private DriveFacade driveFacade;
    
    private String LOGIN_REDIRECT = "redirect:/auth/login";
    private String CAR_LIST_REDIRECT = "redirect:/cars/list";

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
        model.addAttribute("cars", carFacade.findAll());
        return "cars/list";
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

        CarDTO car = carFacade.findById(id);
        List<DriveDTO> drives = driveFacade.findAll();
        for (DriveDTO drive : drives) {
           if(drive.getCar().equals(car)){
            redirectAttributes.addFlashAttribute("alert_danger", "Car \"" + car + "\" is used " +
                    "and cannot be deleted.");
            return CAR_LIST_REDIRECT;
           }
        }
        model.addAttribute("authenticatedUser", authEmployee);
        carFacade.delete(car);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Car \"" + car.getVin() + "\" was deleted.");
        return CAR_LIST_REDIRECT;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("carCreate") CarCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder,
                         HttpServletRequest request) {

        EmployeeDTO authEmployee = (EmployeeDTO) request.getSession().getAttribute("authEmployee");
        if (authEmployee == null || !authEmployee.isAdmin()) {
            log.warn("Failed. Unauthorized");
            redirectAttributes.addFlashAttribute("alert_danger",
                    "Unauthorized.");
            return LOGIN_REDIRECT;
        }

        log.debug("create(tripCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return CAR_LIST_REDIRECT;
        }
        //create trip
        carFacade.create(formBean);
        
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Trip was created");
        return CAR_LIST_REDIRECT;
    }
}