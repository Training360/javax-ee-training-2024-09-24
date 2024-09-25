package employees;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EmployeesService {

    @Inject
    private EmployeesDao employeesDao;

    @Inject
    private EmployeeMapper employeeMapper;

    public List<EmployeeDto> findAll() {
        var employees = employeesDao.findAll();
        return employeeMapper.toEmployeeDtos(employees);
    }

    public EmployeeDto findById(long id) {
        var employee = employeesDao.findById(id);
        return employeeMapper.toEmployeeDto(employee);
    }

    public EmployeeDto create(EmployeeDto employee) {
        employeesDao.findEmployeeByName(employee.getName()).ifPresent(
                e -> {throw new IllegalArgumentException("Employee already exists with id: " + e.getId());}
        );

        var entity = employeeMapper.toEmployee(employee);
        var result = employeesDao.create(entity);
        return employeeMapper.toEmployeeDto(result);
    }

    public EmployeeDto update(EmployeeDto employee) {
        var entity = employeeMapper.toEmployee(employee);
        var result = employeesDao.update(entity);
        return employeeMapper.toEmployeeDto(result);
    }

    public void delete(long id) {
        employeesDao.delete(id);
    }
}
