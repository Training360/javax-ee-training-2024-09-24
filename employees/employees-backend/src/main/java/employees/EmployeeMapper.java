package employees;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface EmployeeMapper {

    List<EmployeeDto> toEmployeeDtos(List<Employee> employees);
}
