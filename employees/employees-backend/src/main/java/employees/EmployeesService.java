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
}
