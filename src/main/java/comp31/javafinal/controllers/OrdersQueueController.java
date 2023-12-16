package comp31.javafinal.controllers;



import comp31.javafinal.model.entities.Order;
import comp31.javafinal.model.entities.OrdersQueue;
import comp31.javafinal.model.repos.CustomerRepo;
import comp31.javafinal.model.repos.OrderRepo;
import comp31.javafinal.model.repos.OrdersQueueRepo;
import comp31.javafinal.services.CustomerService;
import comp31.javafinal.services.OrdersQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrdersQueueController {


    OrdersQueueService ordersQueueService;
    OrdersQueueRepo ordersQueueRepo;
    OrderRepo orderRepo;
    CustomerService customerService;
    // Logger for logging messages
    Logger logger = LoggerFactory.getLogger(OrdersQueueController.class);


    public OrdersQueueController(OrdersQueueService ordersQueueService, OrdersQueueRepo ordersQueueRepo, OrderRepo orderRepo, CustomerService customerService) {

        this.ordersQueueService = ordersQueueService;
        this.ordersQueueRepo = ordersQueueRepo;
        this.orderRepo = orderRepo;
        this.customerService = customerService;
    }

    @GetMapping("/orderForm")
    public String getOrderForm() {
        return "orderForm";
    }
    // Handling the get request for displaying items in a buy view
    @GetMapping("/orderQueue")
    public String getBuy(Model model) {
        model.addAttribute("Orders", ordersQueueService.findAll());
        logger.info(String.valueOf(model.asMap().keySet()));
        return "orderQueue";
    }
    @PostMapping("/addOrder")
    public String addItem(@RequestParam("orderFName") String fName,@RequestParam("orderLName") String lName,
                          @RequestParam("date") String date, @RequestParam("type") String type, @RequestParam("description") String description) {
        ordersQueueRepo.save(new OrdersQueue(fName, lName, type, date, description));
        return "orderForm";
    }

    //mapping for deleting a customer
    @PostMapping("/delete-order")
    public String postDeleteOrder(Model model,@RequestParam("orderId") Integer orderId)
    {
        ordersQueueService.deleteByID(orderId);
        return "redirect:/orderQueue";
    }
    @PostMapping("/approve-order")
    public String postApproveOrder(@RequestParam("orderId") Integer orderId)
    {
        ordersQueueService.changeStatus("Approved", orderId);
        String fName = ordersQueueService.findFNamebyID(orderId);
        String lName = ordersQueueService.findLNamebyID(orderId);
        String status = "Incomplete";
        String date = ordersQueueService.findDateByID(orderId);
        String description = ordersQueueService.findDescriptionByID(orderId);
        String type = ordersQueueService.findTypeByID(orderId);
        orderRepo.save(new Order(fName, lName,status,date,description, type, customerService.getCurrentUser()));
        ordersQueueService.deleteByID(orderId);
        return "redirect:/orderQueue";
    }


}