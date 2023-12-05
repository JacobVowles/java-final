package comp31.javafinal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comp31.javafinal.services.EmployeeService;

@Controller
@RequestMapping
public class EmployeeController {
    
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }   

    @PostMapping("/find-employee")
    public String findEmployee(@RequestParam("employeeNumber") String employeeNumber, @RequestParam("password") String password) {
        
        employeeNumber = employeeNumber.trim();
        password = password.trim();
        // if employeeNumber and password match, then return "redirect:/employee-home"
        Boolean employeeFound = employeeService.findByEmployeeNumberAndPassword(employeeNumber, password);
        if (employeeFound) {
            String role = employeeService.findByEmployeeNumber(employeeNumber).get(0).getRole();
            if (role.equals("Admin")) {
                return "redirect:/admin-home";
            }
            else if (role.equals("Baker")) {
                return "redirect:/baker-home";
            }
            else
            {
                return "redirect:/sales-rep-home";
            }
        }
        else
        {
            return "redirect:/Login-Form";
        }

    }

    @GetMapping("/admin-home")
    public String employeeHome() {
        return "/admin";
    }

    @GetMapping("/baker-home")
    public String bakerHome() {
        return "/uc2html";
    }

    // @GetMapping("/sales-rep-home") nothing here yet
    // public String salesRepHome() {
    //     return "/uc3html";
    // }
}
