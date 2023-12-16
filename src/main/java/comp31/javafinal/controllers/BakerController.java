package comp31.javafinal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comp31.javafinal.model.entities.Order;
import comp31.javafinal.model.repos.OrderRepo;
import comp31.javafinal.model.repos.ProductsRepo;
import comp31.javafinal.services.BakerService;

@Controller
public class BakerController {
    // ALL KIAN
    BakerService bakerService;
    OrderRepo orderRepo;
    ProductsRepo productRepo;

    //Constructor
    public BakerController(BakerService bakerService, OrderRepo orderRepo, ProductsRepo productRepo) {
        this.bakerService = bakerService;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    //The index page
    @GetMapping("/uc2")
    public String uc2() {
        return "uc2";
    }

    //The page for viewing orders
    @GetMapping("/uc2AllOrders")
    public String allOrders(Model model) {
        model.addAttribute("allOrders", bakerService.findAll());
        return "uc2AllOrders";
    }

    //Displays the selected order's additional details
    @GetMapping("/uc2ViewDetails")
    public String ViewDetails(Model model, @RequestParam Integer orderId) {
        Order order = bakerService.findByOrderId(orderId);
        model.addAttribute("order", order);
        return "uc2ViewDetails";
    }
    @GetMapping("/product") //Change Name to Order Menu
    public String getProducts(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "/product";
    }

    //Displays all orders with the incomplete status
    @GetMapping("/uc2ShowIncomplete")
    public String incompleteOrders(Model model) {
        model.addAttribute("allOrders", bakerService.findByStatus("Incomplete"));
        return "/uc2AllOrders";
    }

    //Displays all orders with the complete status
    @GetMapping("/uc2ShowComplete")
    public String completeOrders(Model model) {
        model.addAttribute("allOrders", bakerService.findByStatus("Complete"));
        return "/uc2AllOrders";
    }
    //Changes the status of an order
    @GetMapping("/uc2MarkComplete")
    public String postCreateCustomer(Model model, @RequestParam("orderId") Integer orderId)
    {
        Order order = bakerService.findByOrderId(orderId);
        if(order.getStatus() == "Incomplete")
            order.setStatus("Complete");
        else
            order.setStatus("Incomplete");
        orderRepo.save(order);
        return "redirect:/uc2AllOrders";
    } 
}
