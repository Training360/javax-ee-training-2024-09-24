package employees;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class EmployeesDao {

    private AtomicLong idGenerator = new AtomicLong();

    private List<Employee> employees =
            Collections.synchronizedList(
                    new ArrayList<>(
                            List.of(
                                    new Employee(idGenerator.incrementAndGet(), "John Doe Backend Lombok MapStruct"),
                                    new Employee(idGenerator.incrementAndGet(), "Jane Doe")
                            )
                    )
            );

    public List<Employee> findAll(ListEmployeesFilter listEmployeesFilter) {
        return employees
                .stream()
                .filter(e -> listEmployeesFilter.getNamePrefix().isEmpty() || e.getName().toLowerCase().startsWith(listEmployeesFilter.getNamePrefix().get().toLowerCase()))
                .limit(listEmployeesFilter.getMaxResult())
                .toList();
    }

    public Employee findById(long id) {
        return employees
                .stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElseThrow(() ->
                        new NotFoundException("Employee not found with id: " + id));
    }

    public Employee create(Employee employee) {
        employee.setId(idGenerator.incrementAndGet());
        employees.add(employee);
        return employee;
    }

    public Employee update(Employee employee) {
        var found = findById(employee.getId());
        found.setName(employee.getName());
        return found;
    }

    public void delete(long id) {
        employees.removeIf(e -> e.getId().equals(id));
    }

    public Optional<Employee> findEmployeeByName(String name) {
        return employees
                .stream()
                .filter(e -> e.getName().equals(name))
                .findAny();
    }
}
