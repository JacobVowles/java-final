package comp31.javafinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import comp31.javafinal.model.entities.Customers;

import comp31.javafinal.model.entities.Order;
import comp31.javafinal.model.repos.OrderRepo;

@Service
public class BakerService {
    OrderRepo orderRepo;
    // ALL KIAN
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
    }

