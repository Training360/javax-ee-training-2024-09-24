package employees;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        System.out.println("Validation Exception: " + e.getClass().getName());

        var violations = e.getConstraintViolations()
                .stream().map(v -> new Violation(v.getPropertyPath().toString(), v.getMessage()))
                .collect(Collectors.toSet());

        var problemDetails = ProblemDetail.builder()
                .type("error/validation-error")
                .title("Validation error")
                .detail(e.getMessage())
                .violations(violations)
                .build();

        return Response.status(Response.Status.BAD_REQUEST).entity(problemDetails).build();
    }
}
