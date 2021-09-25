package ru.eatit.common.entity.extract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.eatit.common.LogicalType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorExtractResult {

    private String serviceName;
    private String fieldName;
    private LogicalType expectedType;
    private String errorMessage;
    private String value;
    private Integer arrayIdx;

}
