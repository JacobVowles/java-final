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

import comp31.javafinal.util.EmailWriter;

import comp31.javafinal.model.repos.ProductsRepo;


import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
 
// 90% of this I Jacob (me) made
@Component
public class Initialize implements CommandLineRunner {
    CustomerRepo customerRepo;
    OrderRepo orderRepo;
    ProductsRepo productsRepo; //marco
    EmployeeRepo employeeRepo;
    AccountsRepo accountRepo;

    public Initialize(CustomerRepo customerRepository, OrderRepo orderRepo, AccountsRepo accountRepo, EmployeeRepo employeeRepo, ProductsRepo productsRepo) {
        this.customerRepo = customerRepository;
        this.orderRepo = orderRepo;
        this.accountRepo = accountRepo;
        this.employeeRepo = employeeRepo;
        this.productsRepo = productsRepo;
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

        

        //Default Customers
        Customers cust1 = new Customers("Freddy", "Fish", "1234567890", "FreddyFish@proton.me","123");
        customerRepo.save(cust1);
        Customers cust2 = new Customers("Casey", "Saber", "0987654321", "CaseySaber@hotmail.com","123");
        customerRepo.save(cust2);
        Customers cust3 = new Customers("Jenny", "Said", "1234567890", "JennySaid@gmail.com","123");
        customerRepo.save(cust3);
        //Default Employees
        Employees emp1 = new Employees("T", "J", "e1234", "password", "Admin");
        employeeRepo.save(emp1);
        Employees emp2 = new Employees("Jamie", "Smith", "e1235", "password", "Baker");
        employeeRepo.save(emp2);
        Employees emp3 = new Employees("Feebie", "Safin", "e1236", "password", "Sales Rep");
        employeeRepo.save(emp3);
        //Default Orders
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        //ORDERS ARE KIAN
        
        //orderRepo.save(order1);
        //orderRepo.save(order2);
        //orderRepo.save(order3);

        //MARCO DE MELO
        productsRepo.save(new Products("Baguette", "Made from the french", 7, 3.50));
        productsRepo.save(new Products("French Toast", "French Toast in the Morning", 2, 4.00));
        productsRepo.save(new Products("White Bread", "Plain", 4, 0.10));
        productsRepo.save(new Products("Cake","Really Fatening",9,5.50));
        //MARCO DE MELO


        //Default Admin to log in with
        Employees employee = new Employees("ad","min", "e" + 123,"password","Admin");
        employeeRepo.save(employee);


        //EmailWriter being used to write to a file- testing, needed for product cancelation but didn't get their code
        String path = "email.txt";
        String content = "Your order has been successfully cancelled! If you didn't cancel it, please contact support";
        EmailWriter emailWriter = new EmailWriter();
        emailWriter.writeEmail(path,content);
    }

    public List<Accounts> findAllAccounts() {
        return accountRepo.findAll();
    }
}
