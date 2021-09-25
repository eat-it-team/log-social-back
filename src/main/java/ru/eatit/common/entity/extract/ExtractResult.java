package ru.eatit.common.entity.extract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractResult {
    private String id;
    private JSONObject details;
    private List<ErrorExtractResult> errors;
}
