package comp31.javafinal.services;

import java.util.List;


//import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

//import comp31.javafinal.model.entities.Perms;
import comp31.javafinal.model.entities.Employees;

import comp31.javafinal.model.repos.PermsRepository;

//Ian 
@Service
public class PermsService {
    PermsRepository permsRepository;

    public PermsService(PermsRepository permsRepository)
    {
        this.permsRepository = permsRepository;
    }

    public List<Employees> findAll() {
        return permsRepository.findAll();
    }

    public Employees findByEmployeeNumber(String employeeNumber) 
    {
        return permsRepository.findByEmployeeNumber(employeeNumber);
    }

    public Employees update(Employees perms)
    {
        return permsRepository.save(perms);
    }

    public void createNewEmployee(String employeeNumber, String password, String firstName, String lastName, String role) {
        permsRepository.save(new Employees(firstName,lastName, employeeNumber, password, role));
    }
}