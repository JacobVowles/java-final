package comp31.javafinal.controllers;
import org.springframework.ui.Model;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import comp31.javafinal.model.entities.Products;
import comp31.javafinal.services.AccountService;
import comp31.javafinal.services.CustomerService;
import comp31.javafinal.model.repos.ProductsRepo;



@Controller
@RequestMapping

public class CustomerController {
    // MOST JACOB
    CustomerService customerService;
    ProductsRepo productsRepo; //marco, all references to products are marco's
    AccountService accountsService;
    
    public CustomerController(CustomerService customerService, ProductsRepo productsRepo, AccountService accountsService) {
        this.customerService = customerService;
        this.productsRepo = productsRepo;
        this.accountsService = accountsService;
    }

    @GetMapping("/admin")
    public String uc1() {
        return "admin";
    }

    @GetMapping("/customer-login")
    public String customerLogin() {
        return "customer-login";
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
            return "redirect:/uc3";
        }
        else
        {
            return "redirect:/customer-login";
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
        accountsService.deleteById(customerId);
        customerService.deleteById(customerId);
        return "redirect:/customer-accounts";
    }

      @GetMapping("/uc3") //MARCO DE MELO
     public String getUc3(Model model)
     {
         model.addAttribute("products", productsRepo.findAll());
        return "uc3";
     }
    @GetMapping("/BuyProducts") //MARCO DE MELO
    public String getBuy(Model model) {
        model.addAttribute("products", productsRepo.findAll());
        return "uc3BuyProducts";
    }
    // Handling the post request for buying products
    @PostMapping("/buy-products") //MARCO DE MELO
    public String buyProducts(RedirectAttributes redirectAttributes,
    @RequestParam("productNames") List<String> productNames,
    @RequestParam("quantities") List<Integer> quantities) {
        if (productNames.size() != quantities.size()) {
        redirectAttributes.addFlashAttribute("errorMessage", "Invalid request. Please try again.");
        return "redirect:/BuyProducts";
        }

        // Process each selected product and quantity
        for (int i = 0; i < productNames.size(); i++) {
        String productName = productNames.get(i);
        int qty = quantities.get(i);

        // Updating product quantity and providing feedback messages
        int updatedRows = productsRepo.updateProductQuantity(productName, qty);
        if (updatedRows <= 0) {
        redirectAttributes.addFlashAttribute("errorMessage", "Failed to purchase products. Please try again.");
        return "redirect:/BuyProducts";
        }
}

    redirectAttributes.addFlashAttribute("successMessage", "Products purchased successfully");
    return "redirect:/BuyProducts";
}
    @GetMapping("/addProducts") //MARCO DE MELO
    public String getAddProductsString(Model model)
    {
        return"uc3AddProducts";
    }
@GetMapping("/faq") //MARCO DE MELO
    public String faq(Model model)
    {
        return"faq"; 
    } 
     @PostMapping("/addProduct") //MARCO DE MELO
    public String addProduct(Model model, @RequestParam("productName") String productName,
            @RequestParam("description") String description, @RequestParam("Qty") Integer Qty, @RequestParam("Price") Double price) {
        // Saving a new product to the repository
        productsRepo.save(new Products(productName, description, Qty, price));
        return "uc3AddProducts";
    }
}
