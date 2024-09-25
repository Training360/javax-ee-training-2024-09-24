package employees;

//import jakarta.validation.constraints.NotBlank;

public record EmployeeDto(Long id,
//                          @NotBlank(message = "The name must not be blank!")
                          @ValidName(message = "The first character of the name of the employee must be uppercased!")
                          String name) {

}
