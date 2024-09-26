package training;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

import java.util.List;

@Path("/employees")
@AllArgsConstructor
public class EmployeeResource {

    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EmployeeDto create(EmployeeDto employeeDto) {
        return employeeService.create(employeeDto);
    }
}
