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
@Table(name="Products")
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    Integer id;
    @Column(name ="product_name")
    String productName;
    @Column(name = "desc")
    String description;
    @Column(name ="Qty")
    Integer Qty;
    @Column(name ="price")
    Double price;
    

    public Products(String productName, String description, Integer Qty, Double price) {
        this.productName = productName;
        this.description= description;
        this.Qty = Qty;
        this.price = price;
    }
   
}
//MARCO DE MELO