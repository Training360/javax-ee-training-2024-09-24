package employees;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/employees")
public class EmployeeResource {

    @Inject
    private EmployeesService employeesService;

    // Content disposition
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<EmployeeDto> listEmployees() {
        return employeesService.findAll();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public EmployeeDto findById(@PathParam("id") long id) {
        return employeesService.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid EmployeeDto employee) {
        var result = employeesService.create(employee);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public EmployeeDto update(@PathParam("id") long id, EmployeeDto employee) {
        if (id != employee.getId()) {
            throw new IllegalArgumentException("Id mismatch %d != %d".formatted(id, employee.getId()));
        }
        return employeesService.update(employee);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        employeesService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
