package ru.eatit.social_protection.integration.service.extract.example;


import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import ru.eatit.common.IntegrationDbFieldNames;
import ru.eatit.common.LogicalType;
import ru.eatit.common.api.CustomOutsideServiceExtractor;
import ru.eatit.common.api.DataNormalizeFactory;
import ru.eatit.common.api.DataNormalizer;
import ru.eatit.common.entity.extract.ErrorExtractResult;
import ru.eatit.common.entity.extract.ExtractResult;
import ru.eatit.common.entity.extract.NormalizeResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExampleDataExtractor implements CustomOutsideServiceExtractor<ExampleData> {

    //Мапа соответствия полям из внешней первичной бд - полям из интеграциооной бд
    private static final Map<String, IntegrationDbFieldNames> FIELD_NAMES_MAP = new HashMap<>();

    static {

        FIELD_NAMES_MAP.put("id", IntegrationDbFieldNames.USER_ID);
        FIELD_NAMES_MAP.put("fio", IntegrationDbFieldNames.USER_FIO);
        FIELD_NAMES_MAP.put("inn", IntegrationDbFieldNames.USER_INN);
    }

    private final DataNormalizeFactory dataNormalizeFactory;


    @Override
    public ExtractResult extract(String serviceName, List<ExampleData> rawData) {
        Map<String, JSONObject> detailsMap = new HashMap<>();
        List<ErrorExtractResult> errors = new ArrayList<>();
        int idx = 0;
        for (ExampleData exampleData : rawData) {
            JSONObject jsonObject = new JSONObject();
            LogicalType idLogicalType = FIELD_NAMES_MAP.get("id").getLogicalType();
            DataNormalizer idDataNormalizer = dataNormalizeFactory.getDataNormalizer(idLogicalType);
            NormalizeResult normalizeIdResult = idDataNormalizer.normalize(exampleData.getId());
            if (normalizeIdResult.isSuccess()) {
                String id = normalizeIdResult.getNormalizedValue();

                extractField(serviceName, "fio", exampleData.getFio(), idx, jsonObject, errors);
                extractField(serviceName, "inn", exampleData.getInn(), idx, jsonObject, errors);
                detailsMap.put(id, jsonObject);
            } else {
                errors.add(new ErrorExtractResult(serviceName, "id", idLogicalType, normalizeIdResult.getErrorMessage(), exampleData.getId(), idx));
            }
            idx++;
        }
        return new ExtractResult(detailsMap, errors);
    }

    //Внутри обновляются jsonObject и errors
    private void extractField(String serviceName, String fieldName, String fieldValue, int idx, JSONObject jsonObject, List<ErrorExtractResult> errors) {
        //fio
        LogicalType logicalType = FIELD_NAMES_MAP.get(fieldName).getLogicalType();
        DataNormalizer dataNormalizer = dataNormalizeFactory.getDataNormalizer(logicalType);
        NormalizeResult normalizeResult = dataNormalizer.normalize(fieldValue);
        if (normalizeResult.isSuccess()) {
            jsonObject.put(FIELD_NAMES_MAP.get(fieldName).getName(), normalizeResult.getNormalizedValue());
        } else {
            errors.add(new ErrorExtractResult(serviceName, fieldName, logicalType, normalizeResult.getErrorMessage(), fieldValue, idx));
        }
    }
}
