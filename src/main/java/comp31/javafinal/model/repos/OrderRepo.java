package comp31.javafinal.model.repos;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import comp31.javafinal.model.entities.Order;
import java.util.List;

@Repository
public interface OrderRepo extends ListCrudRepository<Order, Integer> {
    

    //Custom method that returns all orders with a specific status
    List<Order> findByStatus(String status);
    
    //Custom method that finds an order by the order id
    Order findByOrderId(Integer id);
}
