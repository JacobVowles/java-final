package comp31.javafinal.services;


import java.util.List;

import org.springframework.stereotype.Service;

import comp31.javafinal.model.entities.Customers;
import comp31.javafinal.model.repos.CustomerRepo;

@Service
public class CustomerService {
    CustomerRepo customerRepo;
    // ALL JACOB
    public Customers findByCustomerId(Integer customerId) {
        return customerRepo.findByCustomerId(customerId);
    } //made for many to one - Ian

    private Customers currentUser;

    public void setCurrentUser(Customers currentUser) {
        this.currentUser = currentUser;
    }

    public Customers getCurrentUser() {
        return currentUser;
    }



    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    
    public List<Customers> findAll() {
        return customerRepo.findAll();
    }

    public List<Customers> findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    public List<Customers> findByFirstNameLike(String firstName) {
        return customerRepo.findByFirstNameLike(firstName);
    }

    public List<Customers> findByLastName(String lastName) {
        return customerRepo.findByLastName(lastName);
    }

    public List<Customers> findByPhoneNumber(String phoneNumber) {
        return customerRepo.findByPhoneNumber(phoneNumber);
    }

    public List<Customers>findByEmailAndPassword(String email, String password) {
        return customerRepo.findByEmailAndPassword(email, password);
    }
    public List<Customers> findByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepo.findByFirstNameAndLastName(firstName, lastName);
    }
    public Customers findCustomerByEmailAndPassword(String email, String password){return customerRepo.findCustomerByEmailAndPassword(email,password);}
    public void createNewCustomer(String firstName, String lastName, String phoneNumber, String email, String password) {
        customerRepo.save(new Customers(firstName, lastName, phoneNumber, email,password));
    }
    public void deleteById(Integer id) {
        customerRepo.deleteById(id);
    }
}
