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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="order_id")
    Integer orderId;

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
    public Order(String orderFName, String orderLName, String type, String date, String description, String status) {
        this.orderFName = orderFName;
        this.orderLName = orderLName;
        this.type = type;
        this.status = status;
        this.date = date;
        this.description = description;
    }

}
