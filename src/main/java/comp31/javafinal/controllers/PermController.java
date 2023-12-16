package comp31.javafinal.controllers;

import comp31.javafinal.model.entities.Employees;


import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import comp31.javafinal.services.PermsService;



// Ian Haworth
// Controller for updating user permissions
@Controller
@RequestMapping
public class PermController {
    Logger logger = LoggerFactory.getLogger(PermController.class);
    PermsService permsService;

    public PermController(PermsService permsService)
    {
        this.permsService = permsService;
    }

    @GetMapping("/uc4")
    String getRoot(Model model) {
        logger.info("---- At root.");
        Employees perms = new Employees();
        model.addAttribute("perms", perms);
        model.addAttribute("users", permsService.findAll());
        return "perms-admin";
    }

    @GetMapping("changeRoles")
    public String changeRoles() {
        return "perms-change";
    }

    @PostMapping("/changeUserRole")
    public String changeUserRoles(Employees perms, @RequestParam("employeeNumber") String employeeNumber, @RequestParam String newrole, Model model) {
            Employees currentUser = permsService.findByEmployeeNumber(employeeNumber);
            if(currentUser != null)
            {
                currentUser.setRole(newrole);
                permsService.update(currentUser);
            }
        
        model.addAttribute("users", permsService.findAll());
        return "perms-admin";
    }

    @GetMapping("Employees")
    public String employeeChange(Model model) {
        model.addAttribute("users", permsService.findAll());
        return "perms-admin";
    }

    @GetMapping("addEmployee")
    public String addEmployee() {
        return "perms-add-employee";
    }

    @PostMapping("addNewEmployee")
    public String addNewEmployee(Model model, @RequestParam String employeeNumber, @RequestParam String password, @RequestParam String firstName, 
    @RequestParam String lastName, @RequestParam String role) {
        permsService.createNewEmployee(employeeNumber, password, firstName, lastName, role);
        model.addAttribute("users", permsService.findAll());
        return "perms-admin";
    }
    
}
