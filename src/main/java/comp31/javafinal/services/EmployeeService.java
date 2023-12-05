package comp31.javafinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import comp31.javafinal.model.entities.Employees;
import comp31.javafinal.model.repos.EmployeeRepo;

@Service
public class EmployeeService {
    EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public boolean findByEmployeeNumberAndPassword(String employeeNumber, String password) {
        if (employeeRepo.findByEmployeeNumberAndPassword(employeeNumber, password).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public List<Employees> findByEmployeeNumber(String employeeNumber) {
        return employeeRepo.findByEmployeeNumber(employeeNumber);
    }

}
