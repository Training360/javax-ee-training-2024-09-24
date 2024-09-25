package employees;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
//import java.util.Optional;

@Path("/employees")
@Slf4j
public class EmployeeResource {

//    private static Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    @Inject
    private EmployeesService employeesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    // public List<EmployeeDto> listEmployees(@QueryParam("name-prefix") Optional<String> namePrefix) {
    public List<EmployeeDto> listEmployees(@BeanParam ListEmployeesFilter employeesFilter,
                                           @HeaderParam("RequestId") String requestId) {
        log.info("List employees by filter: {}", employeesFilter);
        log.info("Header: {}", requestId);
        return employeesService.findAll(employeesFilter);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
        if (id != employee.id()) {
            throw new IllegalArgumentException("Id mismatch %d != %d".formatted(id, employee.id()));
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
