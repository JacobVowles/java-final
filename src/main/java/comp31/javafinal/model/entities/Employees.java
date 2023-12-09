package comp31.javafinal.model.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "Employee")
@NoArgsConstructor
public class Employees {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@SequenceGenerator(name = "employeeIdGenerator", sequenceName = "employee_id_seq", initialValue = 1000)
@Column(name = "employee_id")
private Integer employeeId;

@Column(name = "first_name")
private String firstName;

@Column(name = "last_name")
private String lastName;

@Column(name = "employeeNumber")
private String employeeNumber;

@Column(name = "password")
private String password;

@Column(name="role")
private String role;


public Employees(String pFirstName, String pLastName, String pEmployeeNumber, String pPassword, String pRole) {
    this.firstName = pFirstName;
    this.lastName = pLastName;
    this.employeeNumber = pEmployeeNumber;
    this.password = pPassword;
    this.role = pRole;
}

}

