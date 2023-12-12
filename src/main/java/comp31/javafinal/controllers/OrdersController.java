package comp31.javafinal.controllers;



import comp31.javafinal.model.entities.OrdersQueue;
import comp31.javafinal.model.repos.OrdersQueueRepo;
import comp31.javafinal.services.OrdersQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrdersController {


    OrdersQueueService ordersQueueService;
    OrdersQueueRepo ordersQueueRepo;
    // Logger for logging messages
    Logger logger = LoggerFactory.getLogger(OrdersController.class);


    public OrdersController(OrdersQueueService ordersQueueService, OrdersQueueRepo ordersQueueRepo) {

        this.ordersQueueService = ordersQueueService;
        this.ordersQueueRepo = ordersQueueRepo;
    }



    // Handling the root endpoint
    @GetMapping("/")
    public String getRoot() {
        return "index";
    }

    // Handling the post request for navigation
    @PostMapping("/goTo")
    public String postFindBy(Model model, @RequestParam("Selected") String selected) {
        // Handling different navigation options
        if (selected.equals("orderApproval")) {

            logger.info(" found : " + selected);
            return "redirect:/" + selected;
        } else if (selected.equals("orderForm")) {
            logger.info(" found : " + selected);
            return "redirect:/" + selected;
        } else {
            return "redirect:/";
        }
    }
    // Handling the get request for displaying the admin view
    @GetMapping("/orderForm")
    public String getAdmin(Model model) {
        return "orderForm";
    }
    // Handling the get request for displaying items in a buy view
    @GetMapping("/orderApproval")
    public String getBuy(Model model) {
        model.addAttribute("Orders", ordersQueueService.findAll());
        return "orderApproval";
    }
    @PostMapping("/addOrder")
    public String addItem(Model model, @RequestParam("orderFName") String fName,@RequestParam("orderLName") String lName,
                          @RequestParam("date") String date, @RequestParam("type") String type) {
        // Saving a new order to the repository
        ordersQueueRepo.save(new OrdersQueue(fName, lName, type, date));
        return "orderForm";
    }

    //mapping for deleting a customer
    @PostMapping("/delete-order")
    public String postDeleteOrder(Model model,@RequestParam("orderId") Integer orderId)
    {
        ordersQueueService.deleteByID(orderId);
        return "redirect:/orderApproval";
    }
    @PostMapping("/approve-order")
    public String postApproveOrder(Model model,@RequestParam("orderId") Integer orderId)
    {
        ordersQueueService.changeStatus("Approved", orderId);
        return "redirect:/orderApproval";
    }


}