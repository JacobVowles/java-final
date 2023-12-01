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
    @Column(name ="item_name")
    String itemName;
    @Column(name = "desc")
    String description;
    @Column(name ="Qty")
    Integer Qty;
    @Column(name ="price")
    Integer price;
    

    public Items(String itemName, String description, Integer Qty, Integer price) {
        this.itemName = itemName;
        this.description= description;
        this.Qty = Qty;
        this.price = price;
    }
   
}
