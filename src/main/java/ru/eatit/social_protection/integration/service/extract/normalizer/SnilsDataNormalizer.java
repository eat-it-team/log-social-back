package ru.eatit.social_protection.integration.service.extract.normalizer;

import org.springframework.stereotype.Service;
import ru.eatit.common.api.DataNormalizer;
import ru.eatit.common.entity.extract.NormalizeResult;

@Service
public class SnilsDataNormalizer implements DataNormalizer {

    @Override
    public NormalizeResult normalize(String rawValue) {
        //TODO: более сложная проверка должна быть
        if (rawValue == null || rawValue.trim() == "") {
            return new NormalizeResult(false, rawValue, "СНИЛС не должен быть null или пустым");
        }

        return new NormalizeResult(true, rawValue.trim(), null);
    }
}
