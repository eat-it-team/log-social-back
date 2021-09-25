package ru.eatit.integration.service.smev.service;

import org.springframework.stereotype.Service;
import ru.eatit.common.api.CustomOutsideServiceExtractor;
import ru.eatit.common.entity.extract.ExtractResult;
import ru.eatit.integration.service.smev.domain.GetAllDataRequest;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;

/**
 * Сервис для нормализации данных и их переименованию к нашим полям
 */
@Service
public class GetAllDataExtractor implements CustomOutsideServiceExtractor<GetAllDataRequest, GetAllDataResponse> {

    @Override
    public ExtractResult extract(String serviceName, GetAllDataRequest request, GetAllDataResponse response) {
        return null;
    }
}
