package comp31.javafinal.services;


import java.util.List;

import org.springframework.stereotype.Service;

import comp31.javafinal.model.entities.CustomerAccount;
import comp31.javafinal.model.repos.CustomerRepo;

@Service
public class CustomerService {
    CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    
    public List<CustomerAccount> findAll() {
        return customerRepo.findAll();
    }

    public List<CustomerAccount> findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    public List<CustomerAccount> findByFirstNameLike(String firstName) {
        return customerRepo.findByFirstNameLike(firstName);
    }

    public List<CustomerAccount> findByLastName(String lastName) {
        return customerRepo.findByLastName(lastName);
    }

    public List<CustomerAccount> findByPhoneNumber(String phoneNumber) {
        return customerRepo.findByPhoneNumber(phoneNumber);
    }

    public List<CustomerAccount> findByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    public void createNewCustomer(String firstName, String lastName, String phoneNumber, String email, String password) {
        customerRepo.save(new CustomerAccount(firstName, lastName, phoneNumber, email,password));
    }
    // public void deleteByEmail(String email) {
    //     customerRepo.deleteByEmail(email); <--- TODO this isn't working for some reason
    // }
    public void deleteById(Integer id) {
        customerRepo.deleteById(id);
    }
}
