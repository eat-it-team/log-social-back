package ru.eatit.integration.service.extract.normalizer;

import org.springframework.stereotype.Service;
import ru.eatit.common.api.DataNormalizer;
import ru.eatit.common.entity.extract.NormalizeResult;
import ru.eatit.common.other.InnChecker;

@Service
public class InnDataNormalizer implements DataNormalizer<String> {

    @Override
    public NormalizeResult normalize(String rawValue) {
        if (rawValue == null) {
            return new NormalizeResult(false, rawValue, "Ошибка при валидации ИНН");
        }
        String data = rawValue.trim();
        boolean isValid = InnChecker.checkInn(data);
        if (isValid) {
            return new NormalizeResult(true, data, null);
        }
        return new NormalizeResult(false, data, "Ошибка при валидации ИНН");
    }
}
