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

    private static final Map<String, GetAllDataResponse> RESPONSE_MAP = new HashMap<>();

    static {
        RESPONSE_MAP.put(
                "1", GetAllDataResponse.builder()
                        .address("426007, Удмуртская Республика, г. Ижевск, ул.Пушкинская, д.214")
                        .id("1")
                        .firstName("Галина")
                        .middleName("Федоровна")
                        .lastName("Константинопольская")
                        .snils("111-111-111 11")
                        .признакМалоимущести(true)
                        .признакПенсионера(true)
                        .признакНаличияСтатусаБезработного(false)
                        .build()
        );
    }

    public GetAllDataResponse getResponse(GetAllDataRequest request) {
        return RESPONSE_MAP.getOrDefault(request.getEsiaUserId(), generateNewUser(request.getEsiaUserId()));
    }

    private GetAllDataResponse generateNewUser(String esiaUserId) {
        var candidate = RESPONSE_MAP.entrySet().stream().findFirst();
        if (candidate.isPresent()) {
            var response = candidate.get().getValue().toBuilder()
                    .id(esiaUserId)
                    .build();
            RESPONSE_MAP.put(esiaUserId, response);
            return response;
        }
        var response = GetAllDataResponse.builder()
                .id(esiaUserId)
                .snils(esiaUserId)
                .build();
        RESPONSE_MAP.put(esiaUserId, response);
        return response;
    }
}
