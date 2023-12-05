package comp31.javafinal.model.repos;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import comp31.javafinal.model.entities.Accounts;
@Repository
public interface AccountsRepo extends ListCrudRepository<Accounts, Integer> {
    
}
