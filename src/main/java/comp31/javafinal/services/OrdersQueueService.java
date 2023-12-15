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
    public String findFNamebyID(Integer id) {return ordersQueueRepo.findOrderFNameById(id);}
    public String findLNamebyID(Integer id) {return ordersQueueRepo.findOrderLNameById(id);}
    public String findStatusByID(Integer id) {return ordersQueueRepo.findStatusById(id);}
    public String findDateByID(Integer id) {return ordersQueueRepo.findDateById(id);}
    public String findDescriptionByID(Integer id) {return ordersQueueRepo.findDescriptionById(id);}
    public String findTypeByID(Integer id){return ordersQueueRepo.findtypeById(id);}
}
