package ru.eatit.integration.service.smev.service;

import org.springframework.stereotype.Service;
import ru.eatit.integration.service.smev.domain.GetAllDataRequest;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Здесь мы заводим MAP<request, моковые ответ), для отдачи пользователя с нужным
 * социальным профилем
 */
@Service
public class GetAllDataServiceMockResponseProvider {

    private static final Map<String, GetAllDataResponse> RESPONSE_MAP = Map.of(
            "1", GetAllDataResponse.builder()
                    .address("ул. Максима Горького, 45")
                    .id("1")
                    .firstName("Галина")
                    .middleName("Федоровна")
                    .lastName("Константинопольская")
                    .признакМалоимущести(true)
                    .признакПенсионера(true)
                    .признакНаличияСтатусаБезработного(false)
                    .build()
    );

    public GetAllDataResponse getResponse(GetAllDataRequest request) {
        return null;
    }
}
