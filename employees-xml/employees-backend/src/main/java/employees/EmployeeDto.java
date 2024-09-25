package employees;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EmployeeDto {

    private Long id;

    @ValidName(message = "The first character of the name of the employee must be uppercased!")
    private String name;



}
