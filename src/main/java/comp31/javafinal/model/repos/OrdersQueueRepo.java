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
    @Query("select orderFName from OrdersQueue where orderId=?1")
    String findOrderFNameById(int id);
    @Query("select orderLName from OrdersQueue where orderId=?1")
    String findOrderLNameById(int id);
    @Query("select status from OrdersQueue where orderId=?1")
    String findStatusById(int id);
    @Query("select date from OrdersQueue where orderId=?1")
    String findDateById(int id);
    @Query("select description from OrdersQueue where orderId=?1")
    String findDescriptionById(int id);
    @Query("select type from OrdersQueue where orderId=?1")
    String findtypeById(int id);
}

