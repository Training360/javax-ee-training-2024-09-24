package employees;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
@NamedQuery(name = "findAll", query = "select e from Employee e")
//@NamedQuery(name = "findById", query = "select e from Employee e where e.id = :id")
@NamedQuery(name = "findEmployeeByName", query = "select e from Employee e where lower(e.name) = lower(:name)")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "emp_name")
    private String name;

}
