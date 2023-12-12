package comp31.javafinal.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name ="Orders")
@NoArgsConstructor
public class OrdersQueue {

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

    public OrdersQueue(String orderFName, String orderLName, String type, String date) {
        this.orderFName = orderFName;
        this.orderLName = orderLName;
        this.type = type;
        this.status = "Pending";
        this.date = date;
    }

}