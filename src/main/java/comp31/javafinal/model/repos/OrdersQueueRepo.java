package comp31.javafinal.model.repos;


import comp31.javafinal.model.entities.OrdersQueue;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersQueueRepo extends ListCrudRepository<OrdersQueue,Integer> {



    // Indicating that this method will modify the database
    @Modifying
    // Specifying that this method should be executed within a transaction
    @Transactional
    // Defining a custom JPQL (Java Persistence Query Language) query
    @Query("update OrdersQueue set status = ?1 where orderId = ?2")
    void changeStatus(String status, int id);

    void deleteById(Integer id);


}