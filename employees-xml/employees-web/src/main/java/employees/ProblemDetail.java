package employees;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProblemDetail {

    private String type;

    private String title;

    private String detail;

    private Set<Violation> violations;
}
