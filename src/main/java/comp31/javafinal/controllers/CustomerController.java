package comp31.javafinal.controllers;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import comp31.javafinal.model.entities.Products;

import comp31.javafinal.services.CustomerService;
import comp31.javafinal.model.repos.ProductsRepo;

@Controller
@RequestMapping
public class CustomerController {

    CustomerService customerService;
    ProductsRepo productsRepo;

    
    public CustomerController(CustomerService customerService, ProductsRepo productsRepo) {
        this.customerService = customerService;
        this.productsRepo = productsRepo;
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
