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
        //TODO заполнить несколько пользователей с разными соц. профилями
    }

    public GetAllDataResponse getResponse(GetAllDataRequest request) {
        return null;
    }
}
