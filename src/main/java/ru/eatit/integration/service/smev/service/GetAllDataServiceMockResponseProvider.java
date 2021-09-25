package ru.eatit.integration.service.smev.service;

import lombok.var;
import org.springframework.stereotype.Service;
import ru.eatit.integration.service.smev.domain.GetAllDataRequest;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
                        .признакНаличияСтатусаБезработного(true)
                        .build()
        );
        RESPONSE_MAP.put(
                "2", GetAllDataResponse.builder()
                        .address("426007, Удмуртская Республика, г. Ижевск, ул. Первомайская, 1")
                        .id("2")
                        .firstName("Павел")
                        .middleName("Николаевич")
                        .lastName("Истоминский")
                        .snils("222-222-222 22")
                        .признакМалоимущести(true)
                        .признакПенсионера(false)
                        .признакНаличияСтатусаБезработного(true)
                        .build()
        );
        RESPONSE_MAP.put(
                "3", GetAllDataResponse.builder()
                        .address("426007, Удмуртская Республика, г. Ижевск, улица Наговицына, 66")
                        .id("3")
                        .firstName("Ольга")
                        .middleName("Александровна")
                        .lastName("Соломина")
                        .snils("333-333-333 33")
                        .признакМалоимущести(true)
                        .признакПенсионера(false)
                        .признакНаличияСтатусаБезработного(false)
                        .build()
        );
        RESPONSE_MAP.put(
                "4", GetAllDataResponse.builder()
                        .address("426007, Удмуртская Республика, г. Ижевск, улица Союзная, 40")
                        .id("4")
                        .firstName("Иннокентий")
                        .middleName("Святославович")
                        .lastName("Богатырев")
                        .snils("444-444-444 44")
                        .признакМалоимущести(true)
                        .признакПенсионера(false)
                        .признакНаличияСтатусаБезработного(false)
                        .build()
        );
        RESPONSE_MAP.put(
                "5", GetAllDataResponse.builder()
                        .address("426007, Удмуртская Республика, г. Ижевск, улица Союзная, 40")
                        .id("5")
                        .firstName("Ирина")
                        .middleName("Сергеевна")
                        .lastName("Петрова")
                        .snils("555-555-555 55")
                        .признакМалоимущести(true)
                        .признакПенсионера(false)
                        .признакНаличияСтатусаБезработного(true)
                        .build()
        );
    }

    public GetAllDataResponse getResponse(GetAllDataRequest request) {
        return RESPONSE_MAP.getOrDefault(request.getEsiaUserId(), generateNewUser(request.getEsiaUserId()));
    }

    private GetAllDataResponse generateNewUser(String esiaUserId) {
        GetAllDataResponse response1 = RESPONSE_MAP.get(esiaUserId);
        if (response1 != null) {
            return response1;
        }
        GetAllDataResponse response = GetAllDataResponse.builder()
                .id(esiaUserId)
                .snils(esiaUserId)
                .build();
        RESPONSE_MAP.put(esiaUserId, response);
        return response;
    }
}
