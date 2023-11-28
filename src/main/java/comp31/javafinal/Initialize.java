package comp31.javafinal;

import org.springframework.stereotype.Component;

import comp31.javafinal.model.entities.CustomerAccount;
import comp31.javafinal.model.repos.CustomerRepo;

import com.github.javafaker.Faker;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
 

@Component
public class Initialize implements CommandLineRunner {
    CustomerRepo customerRepository;

    public Initialize(CustomerRepo customerRepository) {
        this.customerRepository = customerRepository;
    }

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
    }
}
