package training;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public List<EmployeeDto> findAll() {
        return employeeRepository.listAll().stream().map(e -> new EmployeeDto(e.getId(), e.getName())).toList();
    }

    @Transactional
    public EmployeeDto create(EmployeeDto employeeDto) {
        var entity = new Employee(employeeDto.getId(), employeeDto.getName());
        employeeRepository.persist(entity);
        return new EmployeeDto(entity.getId(), entity.getName());
    }
}
