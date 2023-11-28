package comp31.javafinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import comp31.javafinal.model.entities.Order;
import comp31.javafinal.model.repos.OrderRepo;

@Service
public class BakerService {
    OrderRepo orderRepo;

    public BakerService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public List<Order> findByStatus(String status) {
        return orderRepo.findByStatus(status);
    }

    public Order findByOrderId(Integer id) {
        return orderRepo.findByOrderId(id);
    }

    public void addOrder(Integer customerId, String itemType, String flavour, String note, String status) {
        orderRepo.save(new Order(customerId, itemType, flavour, note, status));
    }
}
