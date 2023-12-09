package comp31.javafinal.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Accounts")
@NoArgsConstructor
public class Accounts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer Id;

    @Column(name="account_type")
    private String accountType;

    @OneToOne
    @JoinColumn(name="customer_id")
    // @JoinColumn(referencedColumnName = "customer_id", insertable = false, updatable = false)
    private Customers customerId ;

    @OneToOne
    @JoinColumn(name="employee_id")
    // @JoinColumn( referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employeeId;

    public Accounts(String pAccountType,  Employees pEmployeeId, Customers pCustomerId) {
        this.accountType = pAccountType;
        this.employeeId = pEmployeeId;
        this.customerId = pCustomerId;
    }

}
