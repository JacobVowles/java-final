
package comp31.javafinal.model.repos;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import comp31.javafinal.model.entities.Products;
import jakarta.transaction.Transactional;

public interface ProductsRepo extends CrudRepository<Products, Integer> {
    public List<Products> findAll();

  public List<Products> findByProductName(String productName);
    

// Indicating that this method will modify the database
@Modifying
// Specifying that this method should be executed within a transaction
@Transactional
// Defining a custom JPQL (Java Persistence Query Language) query
@Query("UPDATE Products i SET i.Qty = i.Qty - ?2 WHERE i.productName = ?1 AND i.Qty >= ?2")
// Method to update the quantity of an product in the database
int updateProductQuantity(String productName, int qty);
}
//MARCO DE MELO