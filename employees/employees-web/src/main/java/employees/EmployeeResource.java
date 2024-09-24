package employees;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/employees")
public class EmployeeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDto> listEmployees() {
        return List.of(
                new EmployeeDto(1L, "John Doe"),
                new EmployeeDto(2L, "Jack Doe")
        );
    }
}
