package employees;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EmployeesDao {

    private List<Employee> employees =
            Collections.synchronizedList(
                    new ArrayList<>(
                            List.of(
                                    new Employee(1L, "John Doe Backend Lombok MapStruct"),
                                    new Employee(2L, "Jane Doe")
                            )
                    )
            );

    public List<Employee> findAll() {
        return employees;
    }
}
