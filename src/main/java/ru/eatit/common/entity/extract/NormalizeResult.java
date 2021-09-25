package ru.eatit.common.entity.extract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NormalizeResult {
    private boolean success;
    private String normalizedValue;
    private String errorMessage;
}
