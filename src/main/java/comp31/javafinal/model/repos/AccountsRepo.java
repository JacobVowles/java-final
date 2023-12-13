package comp31.javafinal.model.repos;

import comp31.javafinal.model.entities.Customers;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import comp31.javafinal.model.entities.Accounts;

import java.util.List;

@Repository
public interface AccountsRepo extends ListCrudRepository<Accounts, Integer> {
    List<Accounts> findAll();
}
