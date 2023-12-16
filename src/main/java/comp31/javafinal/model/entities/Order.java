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

    @Column(name = "first_name")
    String orderFName;

    @Column(name ="last_name")
    String orderLName;

    @Column(name = "status")
    String status;

    @Column(name= "date")
    String date;

    @Column(name= "type")
    String type;
    @Column(name="description")
    String description;
    public Order(String orderFName, String orderLName, String status, String date, String description,String type,Int) {
        this.orderFName = orderFName;
        this.orderLName = orderLName;
        this.type = type;
        this.status = status;
        this.date = date;
        this.description = description;
    }

}
