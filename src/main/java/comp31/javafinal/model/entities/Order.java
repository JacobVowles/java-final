package comp31.javafinal.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "bakery_orders")
@NoArgsConstructor
public class Order {

    //Columns for The Order table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "new_order_id")
    private Integer orderId;
    
    @Column(name = "customer_id")
    private Integer customerId;

    
    //When order queue is done attach the three tables 
    /*@ManyToMany
     * @JoinColumn(name="product_id")
     * private Integer productId;
     */



    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    //Constructor
    public Order( Integer customerId,  String note, String status) {
        this.customerId = customerId;
        
        this.note = note;
        this.status = status;
    }

}
