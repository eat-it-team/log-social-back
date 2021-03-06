package ru.eatit.integration.service.extract.example;


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
public class ExampleDataExtractor implements CustomOutsideServiceExtractor<String, ExampleData> {

    //Мапа соответствия полям из внешней первичной бд - полям из интеграциооной бд
    private static final Map<String, IntegrationDbFieldNames> FIELD_NAMES_MAP = new HashMap<>();

    static {

        FIELD_NAMES_MAP.put("id", IntegrationDbFieldNames.USER_ID);
        FIELD_NAMES_MAP.put("fio", IntegrationDbFieldNames.USER_FIO);
        FIELD_NAMES_MAP.put("inn", IntegrationDbFieldNames.USER_INN);
    }

    private final DataNormalizeFactory dataNormalizeFactory;

    @Override
    public ExtractResult extract(String serviceName, String request, ExampleData rawData) {
        List<ErrorExtractResult> errors = new ArrayList<>();

        JSONObject jsonObject = new JSONObject();
        LogicalType idLogicalType = FIELD_NAMES_MAP.get("id").getLogicalType();
        DataNormalizer idDataNormalizer = dataNormalizeFactory.getDataNormalizer(idLogicalType);
        NormalizeResult normalizeIdResult = idDataNormalizer.normalize(rawData.getId());
        if (normalizeIdResult.isSuccess()) {
            String id = normalizeIdResult.getNormalizedValue();

            extractField(serviceName, "fio", rawData.getFio(), jsonObject, errors);
            extractField(serviceName, "inn", rawData.getInn(), jsonObject, errors);
        } else {
            errors.add(new ErrorExtractResult(serviceName, "id", idLogicalType, normalizeIdResult.getErrorMessage(), rawData.getId()));
        }

        return new ExtractResult("id", jsonObject, errors);
    }

    //Внутри обновляются jsonObject и errors
    private void extractField(String serviceName, String fieldName, String fieldValue, JSONObject jsonObject, List<ErrorExtractResult> errors) {
        //fio
        LogicalType logicalType = FIELD_NAMES_MAP.get(fieldName).getLogicalType();
        DataNormalizer dataNormalizer = dataNormalizeFactory.getDataNormalizer(logicalType);
        NormalizeResult normalizeResult = dataNormalizer.normalize(fieldValue);
        if (normalizeResult.isSuccess()) {
            jsonObject.put(FIELD_NAMES_MAP.get(fieldName).getName(), normalizeResult.getNormalizedValue());
        } else {
            errors.add(new ErrorExtractResult(serviceName, fieldName, logicalType, normalizeResult.getErrorMessage(), fieldValue));
        }
    }
}
