package employees;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeesService {

    private static Logger log = LoggerFactory.getLogger(EmployeesService.class);

    @Inject
    private EmployeesDao employeesDao;

    @Inject
    private EmployeeMapper employeeMapper;

    public List<EmployeeDto> findAll(ListEmployeesFilter listEmployeesFilter) {
        log.info("Service find all employees");
        var employees = employeesDao.findAll(listEmployeesFilter);
        return employeeMapper.toEmployeeDtos(employees);
    }

    public EmployeeDto findById(long id) {
        var employee = employeesDao.findById(id);
        return employeeMapper.toEmployeeDto(employee);
    }

    public EmployeeDto create(EmployeeDto employee) {
        employeesDao.findEmployeeByName(employee.name()).ifPresent(
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
