package comp31.javafinal;

import org.springframework.stereotype.Component;

import comp31.javafinal.model.entities.CustomerAccount;
import comp31.javafinal.model.entities.Order;
import comp31.javafinal.model.repos.CustomerRepo;
import comp31.javafinal.model.repos.OrderRepo;

import com.github.javafaker.Faker;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
 
import comp31.database_demo.repos.ItemsRepo;


  
@Component
public class Initialize implements CommandLineRunner {
    CustomerRepo customerRepository;
    OrderRepo orderRepo;
    ItemsRepo itemsRepo;

    public Initialize(CustomerRepo customerRepository, OrderRepo orderRepo, ItemsRepo itemsRepo;) {
        this.customerRepository = customerRepository;
        this.orderRepo = orderRepo;
        this.itemsRepo = itemsRepo;
    }
      fqwqw
    //Faker being used for easy default data
    Faker faker = new Faker();
    String firstName, lastName, phoneNumber, email;

    String[] emailProviders = {"@gmail.com","@hotmail.com","@proton.me","@yahoo.com"};
    Random rand = new Random();

    public void run(String... args) throws Exception {
        //default customers that gets added into the database
        for (int i = 0; i < 13; i++)
        {
            int randomNumber = rand.nextInt(4);
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            customerRepository.save(new CustomerAccount(firstName, lastName, faker.phoneNumber().cellPhone(), firstName + "." + lastName + emailProviders[randomNumber]));
        }

        orderRepo.save(new Order( 1, "Cake", "Chocolate", "Please add balloons and happy birthday", "Incomplete" ));
        orderRepo.save(new Order( 2, "Cake", "Vanilla", "Orange icing and pumpkin drawings", "Incomplete" ));
        orderRepo.save(new Order( 3, "Cake", "Red Velvet", "Hearts and flowers drawn on the cake please", "Complete" ));
        itemsRepo.save(new Items("Baguette", "Made from the french", 7));
        itemsRepo.save(new Items("French Toast", "French Toast in the Morning", 2));
        itemsRepo.save(new Items("White Bread", "Plain", 4));
    }
}
