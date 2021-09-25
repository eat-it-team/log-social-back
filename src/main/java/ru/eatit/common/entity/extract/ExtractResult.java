package ru.eatit.common.entity.extract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractResult {
    private Map<String, JSONObject> detailsMap;
    private List<ErrorExtractResult> errors;
}
