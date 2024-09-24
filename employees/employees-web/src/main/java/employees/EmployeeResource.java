package employees;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/employees")
public class EmployeeResource {

    @Inject
    private EmployeesService employeesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDto> listEmployees() {
        return employeesService.findAll();
    }
}
