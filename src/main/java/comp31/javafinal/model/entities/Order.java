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

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "flavour")
    private String flavour;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    //Constructor
    public Order( Integer customerId, String itemType, String flavour, String note, String status) {
        this.customerId = customerId;
        this.itemType = itemType;
        this.flavour = flavour;
        this.note = note;
        this.status = status;
    }

}
