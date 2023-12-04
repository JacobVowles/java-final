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
@Table(name = "Employee")
@NoArgsConstructor
public class EmployeeAccount {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "employee_id")
private Integer employeeId;

@Column(name = "first_name")
private String firstName;

@Column(name = "last_name")
private String lastName;

@Column(name = "employeeNumber")
private Integer employeeNumber;
    
@Column(name="role")
private String role;

public EmployeeAccount(String pFirstName, String pLastName, Integer pEmployeeNumber, String pRole) {
    this.firstName = pFirstName;
    this.lastName = pLastName;
    this.employeeNumber = pEmployeeNumber;
    this.role = pRole;
}

}

