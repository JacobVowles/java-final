package comp31.javafinal.controllers;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import comp31.javafinal.model.entities.Products;
import comp31.javafinal.model.repos.ProductsRepo;
import comp31.javafinal.services.CustomerService;

@Controller
public class CustomerController {
    ProductsRepo productsRepo;
    CustomerService customerService;

    
    public CustomerController(CustomerService customerService, ProductsRepo productsRepo) {
        this.customerService = customerService;
        this.productsRepo = productsRepo;
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
    @RequestParam("phoneNumber") String phoneNumber)
    {
        customerService.createNewCustomer(firstName, lastName, phoneNumber, email);
        return "redirect:/uc1-customer-accounts";
    } 

    //mapping for deleting a customer
    @PostMapping("/delete-customer")
    public String postDeleteCustomer(Model model, @RequestParam("customerId") Integer customerId)
    {
        customerService.deleteById(customerId);
        return "redirect:/uc1-customer-accounts";
    }

     // Handling the get request for displaying products
    // Handling the get request for displaying products in a buy view
     @GetMapping("/uc3")
     public String getUc3(Model model)
     {

        return "uc3";
     }
    @GetMapping("/uc3BuyProducts") //Change Name to Order Menu
    public String getBuy(Model model) {
        model.addAttribute("products", productsRepo.findAll());
        return "uc3BuyProducts";
    }

    // Handling the post request for buying products
    @PostMapping("/buy-product")  //Change it so it can input multiple arrays? i guess then make it into a order
    public String buyProduct(RedirectAttributes redirectAttributes, @RequestParam("productName") String productName,
            @RequestParam("qty") int qty) {
        // Updating product quantity and providing feedback messages
        int updatedRows = productsRepo.updateProductQuantity(productName, qty);
        if (updatedRows > 0) {
            redirectAttributes.addFlashAttribute("successMessage", "Product purchased successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid product or quantity");
        }
        return "redirect:/uc3BuyProducts";
    }
    @GetMapping("/addProducts")
    public String getAddProductsString(Model model)
    {
        return"uc3AddProducts";
    }

     @PostMapping("/addProduct")
    public String addProduct(Model model, @RequestParam("productName") String productName,
            @RequestParam("description") String description, @RequestParam("Qty") Integer Qty, @RequestParam("Price") Double price) {
        // Saving a new product to the repository
        productsRepo.save(new Products(productName, description, Qty, price));
        return "uc3AddProducts";
    }

}
