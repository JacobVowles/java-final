package comp31.database_demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Items")
@NoArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String itemName;
    String description;
    Integer Qty;
    

    // DO SOMETHING LIKE THIS
    /*  @Id
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
    */
    public Items(String itemName, String description, Integer Qty) {
        this.itemName = itemName;
        this.description= description;
        this.Qty = Qty;
    }
   
}
