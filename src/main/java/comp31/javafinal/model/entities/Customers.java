package comp31.javafinal.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Customer")
@NoArgsConstructor
public class Customers {

    //Columns for customer table
    //ALL JACOB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @Column(name = "password")
    private String password;
    
    public Customers(String pFirstName, String pLastName, String pPhoneNumber,String pEmail,String pPassword) {
        this.firstName = pFirstName;
        this.lastName = pLastName;
        this.phoneNumber = pPhoneNumber;
        this.email = pEmail;
        this.password = pPassword;
    }

}
