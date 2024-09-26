package employees;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.Data;

import java.util.Optional;

@Data
public class ListEmployeesFilter {

    @QueryParam("name-prefix")
    private Optional<String> namePrefix;

    @DefaultValue("20")
    @QueryParam("max-result")
    private int maxResult;
}
