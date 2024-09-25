package employees;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        var problemDetails = ProblemDetail
                .builder()
                .type("error/not-found")
                .title("Not found")
                .detail(e.getMessage())
                .build();

        return Response
                .status(Response.Status.NOT_FOUND)
                .header("Content-Type", "application/problem+json")
                .entity(problemDetails)
                .build();
    }
}
