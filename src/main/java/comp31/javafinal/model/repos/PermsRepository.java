package comp31.javafinal.model.repos;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

//import comp31.javafinal.model.entities.Perms;
import comp31.javafinal.model.entities.Employees;

/**
 * Ian Haworth
 * PermsRepository
 */
public interface PermsRepository extends ListCrudRepository<Employees, Integer> {
    public List<Employees> findAll();
    public Employees findByEmployeeNumber(String employeeNumber);

}