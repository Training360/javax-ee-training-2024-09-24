package employees;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProblemDetail {

    private String type;

    private String title;

    private String detail;
}
