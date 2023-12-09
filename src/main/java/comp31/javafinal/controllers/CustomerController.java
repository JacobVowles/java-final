package comp31.javafinal.controllers;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comp31.javafinal.model.entities.Customers;
import comp31.javafinal.services.CustomerService;

@Controller
@RequestMapping
public class CustomerController {

    CustomerService customerService;

    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/admin")
    public String uc1() {
        return "admin";
    }

    @GetMapping("/customer-accounts")
    public String uc1(Model model) {
        // customerService.deleteByEmail("JaneDoe@gmail.com"); // error of cannot reliably process 'remove' call
        model.addAttribute("customers", customerService.findAll());
        return "customer-accounts";
    }

    @PostMapping("find-customer")
    public String findCustomer(@RequestParam String email, @RequestParam String password) {
        email = email.trim();
        password = password.trim();
        Boolean customerFound = customerService.findByEmailAndPassword(email, password).size() > 0;
        if (customerFound) {
            return "redirect:/customer-home";
        }
        else
        {
            return "redirect:/Login-Form";
        }
    }
    //mapping for filter based on first name, last name, email
    @GetMapping("/uc1filter")
    public String filteredUc1(@RequestParam String filter, @RequestParam String input, Model model)
    {
        if("firstName".equals(filter))
        {
            if(input.equals(""))
            {
                return "redirect:/customer-accounts";
            }
            model.addAttribute("customers", customerService.findByFirstNameLike(input));
        }
        else if("lastName".equals(filter))
        {
            if(input.equals(""))
            {
                return "redirect:/customer-accounts";
            }
            model.addAttribute("customers", customerService.findByLastName(input));
        }
        else if("email".equals(filter))
        {
            if(input.equals(""))
            {
                return "redirect:/customer-accounts";
            }
            model.addAttribute("customers", customerService.findByEmail(input));
        }
        return "customer-accounts";
    }
    @GetMapping ("/create-account")
    String uc1DataEntry()
    {
        return "create-account";
    }

    //mapping for creating a new customer
    @PostMapping("/create-customer")
    public String postCreateCustomer(Model model, @RequestParam("firstName") String firstName,
    @RequestParam("lastName") String lastName,
    @RequestParam("email") String email,
    @RequestParam("phoneNumber") String phoneNumber,
    @RequestParam("password") String password)
    {
        customerService.createNewCustomer(firstName, lastName, phoneNumber, email,password);
        return "redirect:/customer-accounts";
    } 

    //mapping for deleting a customer
    @PostMapping("/delete-customer")
    public String postDeleteCustomer(Model model, @RequestParam("customerId") Integer customerId)
    {
        customerService.deleteById(customerId);
        return "redirect:/customer-accounts";
    }

    //mapping for login
    @GetMapping("/Login-Form")
    public String login() {
        return "Login-Form";
    }


}
