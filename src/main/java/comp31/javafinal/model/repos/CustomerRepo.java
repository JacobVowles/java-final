package comp31.javafinal.model.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import comp31.javafinal.model.entities.CustomerAccount;
import java.util.List;


@Repository
public interface CustomerRepo extends ListCrudRepository<CustomerAccount, Integer> {
// ListCrudRepository lets us use methods in use in the repo classes. (Methods such as save, delete, findAll, etc.)

//Custom methods below

List<CustomerAccount> findByEmail(String email);

List<CustomerAccount> findByFirstName(String firstName);

List<CustomerAccount> findByLastName(String lastName);

List<CustomerAccount> findByPhoneNumber(String phoneNumber);

List<CustomerAccount> findByFirstNameAndLastName(String firstName, String lastName);

@Query("SELECT c FROM CustomerAccount c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%',:firstName,'%'))")
List<CustomerAccount> findByFirstNameLike(String firstName);

void deleteById(Integer id);

}

