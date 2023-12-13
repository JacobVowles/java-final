package comp31.javafinal.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "bakery_orders")
@NoArgsConstructor
public class Order {
    // ALL KIAN
    //Columns for The Order table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "new_order_id")
    private Integer orderId;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Integer customerId;




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
