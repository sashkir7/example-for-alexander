package models.testCase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseDto {

    private Integer id;
    private String name;
    private Boolean automated;
    private Boolean external;
    private Double createdDate;
    private String statusName;
    private String statusColor;

}
