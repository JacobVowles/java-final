package comp31.javafinal.controllers;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comp31.javafinal.services.CustomerService;

@Controller
public class CustomerController {

    CustomerService customerService;

    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/uc1")
    public String uc1() {
        return "uc1";
    }

    @GetMapping("/uc1-customer-accounts")
    public String uc1(Model model) {
        // customerService.deleteByEmail("JaneDoe@gmail.com"); // error of cannot reliably process 'remove' call
        model.addAttribute("customers", customerService.findAll());
        return "uc1-customer-accounts";
    }


    //mapping for filter based on first name, last name, email
    @GetMapping("/uc1filter")
    public String filteredUc1(@RequestParam String filter, @RequestParam String input, Model model)
    {
        if("firstName".equals(filter))
        {
            if(input.equals(""))
            {
                return "redirect:/uc1-customer-accounts";
            }
            model.addAttribute("customers", customerService.findByFirstNameLike(input));
        }
        else if("lastName".equals(filter))
        {
            if(input.equals(""))
            {
                return "redirect:/uc1-customer-accounts";
            }
            model.addAttribute("customers", customerService.findByLastName(input));
        }
        else if("email".equals(filter))
        {
            if(input.equals(""))
            {
                return "redirect:/uc1-customer-accounts";
            }
            model.addAttribute("customers", customerService.findByEmail(input));
        }
        return "uc1-customer-accounts";
    }
    @GetMapping ("/uc1-data-entry")
    String uc1DataEntry()
    {
        return "uc1-data-entry";
    }

    //mapping for creating a new customer
    @PostMapping("/create-customer")
    public String postCreateCustomer(Model model, @RequestParam("firstName") String firstName,
    @RequestParam("lname") String lastName,
    @RequestParam("email") String email,
    @RequestParam("phoneNumber") String phoneNumber,
    @RequestParam("password") String password)
    {
        customerService.createNewCustomer(firstName, lastName, phoneNumber, email,password);
        return "redirect:/uc1-customer-accounts";
    } 

    //mapping for deleting a customer
    @PostMapping("/delete-customer")
    public String postDeleteCustomer(Model model, @RequestParam("customerId") Integer customerId)
    {
        customerService.deleteById(customerId);
        return "redirect:/uc1-customer-accounts";
    }
}
