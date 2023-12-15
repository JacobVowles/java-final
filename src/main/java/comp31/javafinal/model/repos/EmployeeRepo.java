package comp31.javafinal.model.repos;
import org.springframework.data.repository.ListCrudRepository;
import comp31.javafinal.model.entities.Employees;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends ListCrudRepository<Employees, Integer> {
    // ALL JACOB
    List<Employees> findByEmployeeNumber(String employeeNumber);

    List<Employees> findByEmployeeNumberAndPassword(String employeeNumber, String password);

}
