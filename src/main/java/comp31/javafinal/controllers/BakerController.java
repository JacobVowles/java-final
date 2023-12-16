package comp31.javafinal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comp31.javafinal.model.entities.Customers;
import comp31.javafinal.model.entities.Order;
import comp31.javafinal.model.repos.OrderRepo;
import comp31.javafinal.model.repos.ProductsRepo;
import comp31.javafinal.services.BakerService;
import comp31.javafinal.services.CustomerService;

@Controller
public class BakerController {
    // ALL KIAN
    BakerService bakerService;
    CustomerService customerService;
    OrderRepo orderRepo;
    ProductsRepo productRepo;
    
    //Constructor
    public BakerController(BakerService bakerService, OrderRepo orderRepo, ProductsRepo productRepo, CustomerService customerService) {
        this.bakerService = bakerService;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.customerService = customerService;
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

    //The page for adding orders
    @GetMapping("/uc2AddOrder")
    public String AddOrder() {
        return "uc2AddOrder";
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

    //Adds a new order to the list
    @PostMapping("/uc2AddOneOrder")
    public String postCreateCustomer(Model model, 
    @RequestParam("customerId") Integer customerId,
    @RequestParam("note") String note,
    @RequestParam("status") String status)
    {
        Customers customer = customerService.findByCustomerId(customerId);
        bakerService.addOrder(customerId , note, status, customer);
        return "redirect:/uc2AddOrder";
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

    //mapping for deleting a customer
    @PostMapping("/delete-order")
    public String postDeleteOrder(Model model, @RequestParam("orderId") Integer orderId)
    {
        bakerService.deleteById(orderId);
        return "redirect:/OrdersAdmin";
    }

    //The page for viewing orders
    @GetMapping("/OrdersAdmin")
    public String adminOrders(Model model) {
        model.addAttribute("allOrders", bakerService.findAll());
        return "admin-orders";
    }

    //Displays all orders with the incomplete status
    @GetMapping("/uc2ShowIncompleteAdmin")
    public String incompleteOrdersAdmin(Model model) {
        model.addAttribute("allOrders", bakerService.findByStatus("Incomplete"));
        return "/admin-orders";
    }

    //Displays all orders with the complete status
    @GetMapping("/uc2ShowCompleteAdmin")
    public String completeOrdersAdmin(Model model) {
        model.addAttribute("allOrders", bakerService.findByStatus("Complete"));
        return "/admin-orders";
    }

    //The page for viewing orders
    @GetMapping("/uc2AllOrdersAdmin")
    public String allOrdersAdmin(Model model) {
        model.addAttribute("allOrders", bakerService.findAll());
        return "admin-orders";
    }

    //Changes the status of an order
    @GetMapping("/uc2MarkCompleteAdmin")
    public String postCreateCustomerAdmin(Model model, @RequestParam("orderId") Integer orderId)
    {
        Order order = bakerService.findByOrderId(orderId);
        if(order.getStatus() == "Incomplete")
            order.setStatus("Complete");
        else
            order.setStatus("Incomplete");
        orderRepo.save(order);
        return "redirect:/OrdersAdmin";
    } 

    //Displays the selected order's additional details
    @GetMapping("/uc2ViewDetailsAdmin")
    public String ViewDetailsAdmin(Model model, @RequestParam Integer orderId) {
        Order order = bakerService.findByOrderId(orderId);
        model.addAttribute("order", order);
        return "admin-orders-details";
    }
}
