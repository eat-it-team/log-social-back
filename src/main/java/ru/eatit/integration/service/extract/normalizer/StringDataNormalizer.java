package ru.eatit.integration.service.extract.normalizer;

import org.springframework.stereotype.Service;
import ru.eatit.common.api.DataNormalizer;
import ru.eatit.common.entity.extract.NormalizeResult;

@Service
public class StringDataNormalizer implements DataNormalizer<String> {

    @Override
    public NormalizeResult normalize(String rawValue) {
        if (rawValue == null || rawValue.trim() == "") {
            return new NormalizeResult(false, rawValue, "Строка не должна быть null или пустой");
        }

        return new NormalizeResult(true, rawValue.trim(), null);
    }
}
