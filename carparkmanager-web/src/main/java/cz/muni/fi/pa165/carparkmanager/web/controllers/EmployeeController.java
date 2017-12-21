package cz.muni.fi.pa165.carparkmanager.web.controllers;

import cz.muni.fi.pa165.carparkmanager.api.dto.EmployeeDTO;
import cz.muni.fi.pa165.carparkmanager.api.facade.EmployeeFacade;
import static cz.muni.fi.pa165.carparkmanager.persistence.enums.UserRoleEnum.ADMINISTRATOR;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
 * @author Jaroslav Bonco
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeFacade employeeFacade;
    
    @Autowired
    private HttpSession session;
    
    private String LOGIN_REDIRECT = "redirect:/auth/login";
    private String EMPLOYEE_LIST_REDIRECT = "redirect:/employees/list";
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("employee.findAll()");
        model.addAttribute("employees", employeeFacade.findAll());

        EmployeeDTO employee = EmployeeDTO.class.cast(session.getAttribute("authenticated"));
        if (employee != null) {
            if (employeeFacade.findById(employee.getId()).getUserRole() == ADMINISTRATOR) {
                model.addAttribute("Admin", employee.getLogin());
            } else {
                model.addAttribute("User", employee.getLogin());
            }
        }
        if (employee.getUserRole() != ADMINISTRATOR)
        {
            return LOGIN_REDIRECT;
        }
        else {
            return "employees/list";
        }
        
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        log.debug("employee.create() GET Request");
        model.addAttribute("employeeCreate", new EmployeeDTO());
        
        EmployeeDTO employee = EmployeeDTO.class.cast(session.getAttribute("authenticated"));
        if (employee != null) {
            if (employeeFacade.findById(employee.getId()).getUserRole() == ADMINISTRATOR) {
                model.addAttribute("Admin", employee.getLogin());
            } else {
                model.addAttribute("User", employee.getLogin());
            }
        }
        return "employee/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("employeeCreate") EmployeeDTO employeeForm,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder){
        log.debug("employee.create() GET Request", employeeForm);
        
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            model.addAttribute("employeeCreate", employeeForm);
            return "employee/create";
        }
        
        if(employeeFacade.findEmployeeByLogin(employeeForm.getLogin()) != null) {
            redirectAttributes.addFlashAttribute("alert_warning", "Chosen login" + employeeForm.getLogin()+ "is not available.");
            return "redirect:" + uriBuilder.path("/employee/create").build().toUriString();
        }
        if(employeeForm.getBirthDate() == null || employeeForm.getFirstname() == null || employeeForm.getUserRole() == null ||
                employeeForm.getPasswordHash() == null || employeeForm.getSurname() == null || employeeForm.getLogin() == null) {
            redirectAttributes.addFlashAttribute("alert_warning", "All fields are mandatory!");
            return "redirect:" + uriBuilder.path("/employee/create").build().toUriString();
        }
        
        Long id = employeeFacade.create(employeeForm, employeeForm.getPasswordHash());
        redirectAttributes.addFlashAttribute("alert_success", "Employee " + id + " was created");
        return "redirect:" + uriBuilder.path("/employee/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("employee.findById()");
        EmployeeDTO employeeView = employeeFacade.findById(id);
        model.addAttribute("employee", employeeView);
        EmployeeDTO employee = EmployeeDTO.class.cast(session.getAttribute("authenticated"));
        if (employee != null) {
            if (employeeFacade.findById(employee.getId()).getUserRole() == ADMINISTRATOR) {
                model.addAttribute("Admin", employee.getLogin());
            } else {
                model.addAttribute("User", employee.getLogin());
            }
        }
        return "employee/view";
    }

    @RequestMapping(value = "/delete/{id}/{Admin}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,@PathVariable String Admin,
                         Model model,
                         UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        log.debug("employee.delete");
        EmployeeDTO employee = employeeFacade.findById(id);
        EmployeeDTO logged = employeeFacade.findEmployeeByLogin(Admin);
        employeeFacade.delete(employee);

        redirectAttributes.addFlashAttribute("alert_success", "Employee successfully deleted.");
        if (Objects.equals(employee.getId(), logged.getId()))
        {
            return "redirect:" + uriBuilder.path("/logout/logout").toUriString();
        }else{
            return "redirect:" + uriBuilder.path("/employee/list").toUriString();
        }
        
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") Long id) {
        model.addAttribute("employee", employeeFacade.findById(id));
        return "redirect:/employee/view/" + employeeFacade.findById(id).getId();
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String change(Model model, @PathVariable("id") Long id) {
        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("data", employeeFacade.findById(id));
        model.addAttribute("firstname", employeeFacade.findById(id).getFirstname());
        model.addAttribute("surname", employeeFacade.findById(id).getSurname());
        model.addAttribute("employeeType", employeeFacade.findById(id).getUserRole());
        model.addAttribute("login",employeeFacade.findById(id).getLogin());
        model.addAttribute("dateOfBirth",employeeFacade.findById(id).getBirthDate());
        
        EmployeeDTO employee = EmployeeDTO.class.cast(session.getAttribute("authenticated"));
        if (employee != null) {
            if (employeeFacade.findById(employee.getId()).getUserRole() == ADMINISTRATOR) {
                model.addAttribute("Admin", employee.getLogin());
            } else {
                model.addAttribute("User", employee.getLogin());
            }
        }
        return "employee/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("employee") EmployeeDTO formBean, @PathVariable("id") Long id, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        formBean.setId(id);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            model.addAttribute("employee",formBean);
            return "employee/edit";
        }
        
        if (formBean.getPasswordHash().isEmpty())
        {
            String passwordHash = employeeFacade.findById(id).getPasswordHash();
            formBean.setPasswordHash(passwordHash);
        }
        
        if(employeeFacade.findEmployeeByLogin(formBean.getLogin()) != null) {
            redirectAttributes.addFlashAttribute("alert_warning", "Chosen login" +formBean.getLogin()+ "is not available.");
            return "redirect:" + uriBuilder.path("/employee/edit/{id}").buildAndExpand(id).encode().toUriString();
        }
        
        if(formBean.getBirthDate() == null || formBean.getFirstname() == null || formBean.getUserRole() == null || 
                formBean.getSurname() == null || formBean.getLogin() == null) {
            redirectAttributes.addFlashAttribute("alert_warning", "All fields except password are mandatory!");
            return "redirect:" + uriBuilder.path("/employee/edit/{id}").buildAndExpand(id).encode().toUriString();
        }
        
        return view(model, employeeFacade.update(formBean).getId());
    }
    // TODO create, update, delete
   
}