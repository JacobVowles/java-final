package comp31.javafinal.services;

import java.util.List;


//import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

//import comp31.javafinal.model.entities.Perms;
import comp31.javafinal.model.entities.Employees;

import comp31.javafinal.model.repos.PermsRepository;

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



    public String login(Employees perms, String password)
    {
        String returnPage;

        if (perms == null) {
            returnPage = "perms-login";
        } else if(password.equals(perms.getPassword())) {   
            if ("Admin".equals(perms.getRole()))
                returnPage = "perms-" + perms.getRole();
            else
                returnPage = "perms-other";                                   
        }else {
            returnPage = "perms-login"; 
        }

        return returnPage;
    }

    public Employees update(Employees perms)
    {
        return permsRepository.save(perms);
    }
}
