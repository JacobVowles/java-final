package comp31.javafinal;

import org.springframework.stereotype.Component;

import comp31.javafinal.model.entities.CustomerAccount;
import comp31.javafinal.model.entities.Order;
import comp31.javafinal.model.repos.CustomerRepo;
import comp31.javafinal.model.repos.OrderRepo;

import com.github.javafaker.Faker;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
 

@Component
public class Initialize implements CommandLineRunner {
    CustomerRepo customerRepository;
    OrderRepo orderRepo;

    public Initialize(CustomerRepo customerRepository, OrderRepo orderRepo) {
        this.customerRepository = customerRepository;
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
            String password = "";
            for (int j = 0; j < 8; j++)
            {
                int randomIndex = rand.nextInt(passwordChars.length());
                password += passwordChars.charAt(randomIndex);
            }
            int randomNumber = rand.nextInt(4);
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            customerRepository.save(new CustomerAccount(firstName, lastName, faker.phoneNumber().cellPhone(), firstName + "." + lastName + emailProviders[randomNumber], password));
        }

        orderRepo.save(new Order( 1, "Cake", "Chocolate", "Please add balloons and happy birthday", "Incomplete" ));
        orderRepo.save(new Order( 2, "Cake", "Vanilla", "Orange icing and pumpkin drawings", "Incomplete" ));
        orderRepo.save(new Order( 3, "Cake", "Red Velvet", "Hearts and flowers drawn on the cake please", "Complete" ));



    }
}
