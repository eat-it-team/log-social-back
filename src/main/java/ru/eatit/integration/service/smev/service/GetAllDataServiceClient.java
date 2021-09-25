package ru.eatit.integration.service.smev.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.eatit.common.api.CustomOutsideServiceSyncClient;
import ru.eatit.integration.service.smev.domain.GetAllDataRequest;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;

/**
 * Получает данные через СМЭВ
 * В рамках хакатона решили сделать моковую реализацию,
 * которая получит все данные которые нам нужны для создания профиля бедности
 * (В реальной жизни это будут несколько запросов)
 * В боевом решение тут будут:
 * посылка запроса по REST или SOAP к госуслугам или сервисам, доступным по СМЭВ
 * получение результата
 */
@Service
@AllArgsConstructor
public class GetAllDataServiceClient implements CustomOutsideServiceSyncClient<GetAllDataRequest, GetAllDataResponse> {

    private final GetAllDataServiceMockResponseProvider mockResponseProvider;

    @Override
    public GetAllDataResponse getData(GetAllDataRequest request) {
        return mockResponseProvider.getResponse(request);
    }
}
