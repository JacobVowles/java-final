package comp31.database_demo.repos;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import comp31.database_demo.model.Items;
import jakarta.transaction.Transactional;

public interface ItemsRepo extends CrudRepository<Items, Integer> {
    public List<Items> findAll();

  public List<Items> findByItemName(String itemName);
    

// Indicating that this method will modify the database
@Modifying
// Specifying that this method should be executed within a transaction
@Transactional
// Defining a custom JPQL (Java Persistence Query Language) query
@Query("UPDATE Items i SET i.Qty = i.Qty - ?2 WHERE i.itemName = ?1 AND i.Qty >= ?2")
// Method to update the quantity of an item in the database
int updateItemQuantity(String itemName, int qty);
}
