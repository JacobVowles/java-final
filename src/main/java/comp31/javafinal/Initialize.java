package comp31.javafinal;

import org.springframework.stereotype.Component;

import comp31.javafinal.model.entities.Accounts;
import comp31.javafinal.model.entities.Customers;
import comp31.javafinal.model.entities.Employees;
import comp31.javafinal.model.entities.Order;
import comp31.javafinal.model.entities.Products;
import comp31.javafinal.model.repos.AccountsRepo;
import comp31.javafinal.model.repos.CustomerRepo;
import comp31.javafinal.model.repos.EmployeeRepo;
import comp31.javafinal.model.repos.OrderRepo;
import comp31.javafinal.model.repos.ProductsRepo;

import com.github.javafaker.Faker;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
 

@Component
public class Initialize implements CommandLineRunner {
    CustomerRepo customerRepo;
    OrderRepo orderRepo;
    ProductsRepo productsRepo;
    EmployeeRepo employeeRepo;
    AccountsRepo accountRepo;

    public Initialize(CustomerRepo customerRepository, OrderRepo orderRepo) {
        this.customerRepo = customerRepository;
        this.orderRepo = orderRepo;
    }

    //Faker being used for easy default data
    Faker faker = new Faker();
    String firstName, lastName, phoneNumber, email;

    String[] emailProviders = {"@gmail.com","@hotmail.com","@proton.me","@yahoo.com"};
    String[] roles = {"Admin", "Baker", "Sales Rep"};
    Random rand = new Random();

    

    String passwordChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

    public void run(String... args) throws Exception {
        //default customers that gets added into the database
        for (int i = 0; i < 13; i++)
        {
            Customers customer;
            Accounts account;
            String password = "";
            for (int j = 0; j < 8; j++)
            {
                int randomIndex = rand.nextInt(passwordChars.length());
                password += passwordChars.charAt(randomIndex);
            }
            int randomNumber = rand.nextInt(4);
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            customer = new Customers(firstName, lastName, faker.phoneNumber().cellPhone(), firstName + "." + lastName + emailProviders[randomNumber], password);
            customerRepo.save(customer);
            account = new Accounts("Customer", null, customer);
            accountRepo.save(account);
        }
        for(int i= 0; i < 5; i++)
        {
            String password = "";
            Employees employee;
            Accounts account;
            for (int j = 0; j < 8; j++)
            {
                int randomIndex = rand.nextInt(passwordChars.length());
                password += passwordChars.charAt(randomIndex);
            }
            int employeeNumber = rand.nextInt(1000);
            int randomNumber = rand.nextInt(3);
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            
            employee = new Employees(firstName,lastName, "e" + employeeNumber,password,roles[randomNumber]);
            employeeRepo.save(employee);
            account = new Accounts("Employee", employee, null);
            accountRepo.save(account);
        }

        Order order1 = new Order( 1, "Cake", "Chocolate", "Please add balloons and happy birthday", "Incomplete" );
        Order order2 = new Order( 2, "Cake", "Vanilla", "Orange icing and pumpkin drawings", "Incomplete" );
        Order order3 = new Order( 3, "Cake", "Red Velvet", "Hearts and flowers drawn on the cake please", "Complete" );
        orderRepo.save(order1);
        productsRepo.save(new Products("Baguette", "Made from the french", 7, 3.50));
        productsRepo.save(new Products("French Toast", "French Toast in the Morning", 2, 4.00));
        productsRepo.save(new Products("White Bread", "Plain", 4, 0.10));
        orderRepo.save(order2);
        orderRepo.save(order3);

    }
}
