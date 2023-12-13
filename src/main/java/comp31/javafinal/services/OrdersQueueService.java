package comp31.javafinal.services;


import comp31.javafinal.model.entities.OrdersQueue;
import comp31.javafinal.model.repos.OrdersQueueRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersQueueService {
    OrdersQueueRepo ordersQueueRepo;

    public OrdersQueueService(OrdersQueueRepo ordersQueueRepo) {this.ordersQueueRepo = ordersQueueRepo;}

    public List<OrdersQueue> findAll() {
        return ordersQueueRepo.findAll();
    }
    public void changeStatus(String status, Integer id) {ordersQueueRepo.changeStatus(status,id);}

    public void deleteByID(Integer id) {ordersQueueRepo.deleteById(id);}


}
