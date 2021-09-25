package ru.eatit.integration.service.smev.service;

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
import ru.eatit.integration.service.dadata.DadataService;
import ru.eatit.integration.service.dadata.entity.Geo;
import ru.eatit.integration.service.smev.domain.GetAllDataRequest;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;
import ru.redcom.lib.integration.api.client.dadata.DaDataClient;

import java.util.*;

/**
 * Сервис для нормализации данных и их переименованию к нашим полям
 */
@Service
@RequiredArgsConstructor
public class GetAllDataExtractor implements CustomOutsideServiceExtractor<GetAllDataRequest, GetAllDataResponse> {

    //Мапа соответствия полям из внешней первичной бд - полям из интеграционной бд
    private static final Map<String, IntegrationDbFieldNames> FIELD_NAMES_MAP = new HashMap<>();

    static {
        FIELD_NAMES_MAP.put("firstName", IntegrationDbFieldNames.FIRST_NAME);
        FIELD_NAMES_MAP.put("lastName", IntegrationDbFieldNames.LAST_NAME);
        FIELD_NAMES_MAP.put("middleName", IntegrationDbFieldNames.MIDDLE_NAME);
        FIELD_NAMES_MAP.put("birthDate", IntegrationDbFieldNames.USER_BIRTH_DATE);
        FIELD_NAMES_MAP.put("address", IntegrationDbFieldNames.ADDRESS);
        FIELD_NAMES_MAP.put("birthPlace", IntegrationDbFieldNames.BIRTH_PLACE);
        FIELD_NAMES_MAP.put("mobilePhone", IntegrationDbFieldNames.MOBILE_PHONE);
        FIELD_NAMES_MAP.put("email", IntegrationDbFieldNames.EMAIL);
        FIELD_NAMES_MAP.put("passport", IntegrationDbFieldNames.PASSPORT);
        FIELD_NAMES_MAP.put("snils", IntegrationDbFieldNames.USER_SNILS);
        FIELD_NAMES_MAP.put("inn", IntegrationDbFieldNames.USER_INN);
        FIELD_NAMES_MAP.put("gender", IntegrationDbFieldNames.GENDER);
        FIELD_NAMES_MAP.put("updateDate", IntegrationDbFieldNames.UPDATE_DATE);
        FIELD_NAMES_MAP.put("признакБеременности", IntegrationDbFieldNames.признакБеременности);
        FIELD_NAMES_MAP.put("признакМалоимущести", IntegrationDbFieldNames.признакМалоимущести);
        FIELD_NAMES_MAP.put("признакПенсионера", IntegrationDbFieldNames.признакПенсионера);

        FIELD_NAMES_MAP.put("признакНаличияИнвалидности", IntegrationDbFieldNames.признакНаличияИнвалидности);
        FIELD_NAMES_MAP.put("признакНаличияИнвалидностиIгруппы", IntegrationDbFieldNames.признакНаличияИнвалидностиIгруппы);
        FIELD_NAMES_MAP.put("признакНаличияИнвалидностиIIгруппы", IntegrationDbFieldNames.признакНаличияИнвалидностиIIгруппы);
        FIELD_NAMES_MAP.put("признакНаличияИнвалидностиIIIгруппы", IntegrationDbFieldNames.признакНаличияИнвалидностиIIIгруппы);


        FIELD_NAMES_MAP.put("признакТрудоспособности", IntegrationDbFieldNames.признакТрудоспособности);
        FIELD_NAMES_MAP.put("признакНахожденияВДекретномОтпуске", IntegrationDbFieldNames.признакНахожденияВДекретномОтпуске);
        FIELD_NAMES_MAP.put("признакНаличияСтатусаБезработного", IntegrationDbFieldNames.признакНаличияСтатусаБезработного);

        FIELD_NAMES_MAP.put("признакПожилогоВозраста", IntegrationDbFieldNames.признакПожилогоВозраста);
        FIELD_NAMES_MAP.put("признакОдинокийРодитель", IntegrationDbFieldNames.признакОдинокийРодитель);
        FIELD_NAMES_MAP.put("признакЖертваРепрессий", IntegrationDbFieldNames.признакЖертваРепрессий);

        FIELD_NAMES_MAP.put("признакВетеранТруда", IntegrationDbFieldNames.признакВетеранТруда);
        FIELD_NAMES_MAP.put("признакТруженикТыла", IntegrationDbFieldNames.признакТруженикТыла);
        FIELD_NAMES_MAP.put("признакНаличияНесовершеннолетнегоРебенка", IntegrationDbFieldNames.признакНаличияНесовершеннолетнегоРебенка);

        FIELD_NAMES_MAP.put("признакЖенатостиЗамужнести", IntegrationDbFieldNames.признакЖенатостиЗамужнести);
        FIELD_NAMES_MAP.put("признакНаличияРебенкаИнвалида", IntegrationDbFieldNames.признакНаличияРебенкаИнвалида);
        FIELD_NAMES_MAP.put("признакУклоненияОтАлиментовВторогоРодителя", IntegrationDbFieldNames.признакУклоненияОтАлиментовВторогоРодителя);
        FIELD_NAMES_MAP.put("признакОпекунства", IntegrationDbFieldNames.признакОпекунства);
        FIELD_NAMES_MAP.put("признакПопечительства", IntegrationDbFieldNames.признакПопечительства);
        FIELD_NAMES_MAP.put("geo_address", IntegrationDbFieldNames.GEO_ADDRESS);

    }

    private final DataNormalizeFactory dataNormalizeFactory;
    private final DadataService dadataService;


    @Override
    public ExtractResult extract(String serviceName, GetAllDataRequest request, GetAllDataResponse response) {

        List<ErrorExtractResult> errors = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        extractField(serviceName, "firstName", response.getFirstName(), jsonObject, errors);
        extractField(serviceName, "lastName", response.getLastName(), jsonObject, errors);
        extractField(serviceName, "middleName", response.getMiddleName(), jsonObject, errors);
        extractField(serviceName, "lastName", response.getLastName(), jsonObject, errors);
        extractField(serviceName, "birthDate", response.getBirthDate(), jsonObject, errors);
        extractField(serviceName, "address", response.getAddress(), jsonObject, errors);
        extractField(serviceName, "birthPlace", response.getBirthPlace(), jsonObject, errors);
        extractField(serviceName, "mobilePhone", response.getMobilePhone(), jsonObject, errors);
        extractField(serviceName, "email", response.getEmail(), jsonObject, errors);
        extractField(serviceName, "passport", response.getPassport(), jsonObject, errors);
        extractField(serviceName, "snils", response.getSnils(), jsonObject, errors);
        extractField(serviceName, "inn", response.getInn(), jsonObject, errors);
        extractField(serviceName, "snils", response.getSnils(), jsonObject, errors);
        extractField(serviceName, "inn", response.getInn(), jsonObject, errors);
        extractField(serviceName, "gender", response.getGender(), jsonObject, errors);
        extractField(serviceName, "updateDate", new Date(response.getUpdateDate()).toString(), jsonObject, errors);
        extractField(serviceName, "признакБеременности", response.getПризнакБеременности(), jsonObject, errors);
        extractField(serviceName, "признакМалоимущести", response.getПризнакМалоимущести(), jsonObject, errors);
        extractField(serviceName, "признакПенсионера", response.getПризнакПенсионера(), jsonObject, errors);
        extractField(serviceName, "признакНаличияИнвалидности", response.getПризнакНаличияИнвалидности(), jsonObject, errors);
        extractField(serviceName, "признакНаличияИнвалидностиIгруппы", response.getПризнакНаличияИнвалидностиIгруппы(), jsonObject, errors);
        extractField(serviceName, "признакНаличияИнвалидностиIIгруппы", response.getПризнакНаличияИнвалидностиIIгруппы(), jsonObject, errors);
        extractField(serviceName, "признакНаличияИнвалидностиIIIгруппы", response.getПризнакНаличияИнвалидностиIIIгруппы(), jsonObject, errors);
        extractField(serviceName, "признакТрудоспособности", response.getПризнакТрудоспособности(), jsonObject, errors);
        extractField(serviceName, "признакНахожденияВДекретномОтпуске", response.getПризнакНахожденияВДекретномОтпуске(), jsonObject, errors);
        extractField(serviceName, "признакНаличияСтатусаБезработного", response.getПризнакНаличияСтатусаБезработного(), jsonObject, errors);


        extractField(serviceName, "признакПожилогоВозраста", response.getПризнакЖертваРепрессий(), jsonObject, errors);
        extractField(serviceName, "признакОдинокийРодитель", response.getПризнакОдинокийРодитель(), jsonObject, errors);
        extractField(serviceName, "признакЖертваРепрессий", response.getПризнакЖертваРепрессий(), jsonObject, errors);

        extractField(serviceName, "признакВетеранТруда", response.getПризнакВетеранТруда(), jsonObject, errors);
        extractField(serviceName, "признакТруженикТыла", response.getПризнакТруженикТыла(), jsonObject, errors);
        extractField(serviceName, "признакНаличияНесовершеннолетнегоРебенка", response.getПризнакНаличияНесовершеннолетнегоРебенка(), jsonObject, errors);

        extractField(serviceName, "признакВетеранТруда", response.getПризнакВетеранТруда(), jsonObject, errors);
        extractField(serviceName, "признакТруженикТыла", response.getПризнакТруженикТыла(), jsonObject, errors);
        extractField(serviceName, "признакНаличияНесовершеннолетнегоРебенка", response.getПризнакНаличияНесовершеннолетнегоРебенка(), jsonObject, errors);

        extractField(serviceName, "признакЖенатостиЗамужнести", response.getПризнакЖенатостиЗамужнести(), jsonObject, errors);
        extractField(serviceName, "признакНаличияРебенкаИнвалида", response.getПризнакНаличияРебенкаИнвалида(), jsonObject, errors);
        extractField(serviceName, "признакУклоненияОтАлиментовВторогоРодителя", response.getПризнакУклоненияОтАлиментовВторогоРодителя(), jsonObject, errors);
        extractField(serviceName, "признакОпекунства", response.getПризнакОпекунства(), jsonObject, errors);
        extractField(serviceName, "признакПопечительства", response.getПризнакПопечительства(), jsonObject, errors);

        String address = (String) jsonObject.get(IntegrationDbFieldNames.ADDRESS.getName());
        if (address != null) {
            Geo coordinates = dadataService.getCoordinates(address);
            if (coordinates != null) {
                extractField(serviceName, "geo_address", coordinates, jsonObject, errors);
            }
        }

        return new ExtractResult(request.getEsiaUserId(), jsonObject, errors);
    }

    //Внутри обновляются jsonObject и errors, которые впоследствии уйдут в ответ
    private void extractField(String serviceName, String fieldName, Object fieldValue, JSONObject jsonObject, List<ErrorExtractResult> errors) {
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
