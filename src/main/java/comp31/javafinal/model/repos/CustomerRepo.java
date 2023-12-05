package comp31.javafinal.model.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import comp31.javafinal.model.entities.Customers;
import java.util.List;


@Repository
public interface CustomerRepo extends ListCrudRepository<Customers, Integer> {
// ListCrudRepository lets us use methods in use in the repo classes. (Methods such as save, delete, findAll, etc.)

//Custom methods below

List<Customers> findByEmail(String email);

List<Customers> findByFirstName(String firstName);

List<Customers> findByLastName(String lastName);

List<Customers> findByPhoneNumber(String phoneNumber);

List<Customers> findByFirstNameAndLastName(String firstName, String lastName);

@Query("SELECT c FROM Customers c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%',:firstName,'%'))")
List<Customers> findByFirstNameLike(String firstName);

void deleteById(Integer id);

}

