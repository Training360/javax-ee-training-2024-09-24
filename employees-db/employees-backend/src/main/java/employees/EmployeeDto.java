package employees;

//import jakarta.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record EmployeeDto(Long id,
//                          @NotBlank(message = "The name must not be blank!")
                          @Schema(examples = {"John Doe", "Jack Doe"}, example = "John Doe Deprecated")
                          @ValidName(message = "The first character of the name of the employee must be uppercased!")
                          String name) {

}
