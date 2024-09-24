package employees;

import jakarta.validation.constraints.NotBlank;

public record EmployeeDto(Long id, @NotBlank(message = "The name must not be blank!") String name) {

}
