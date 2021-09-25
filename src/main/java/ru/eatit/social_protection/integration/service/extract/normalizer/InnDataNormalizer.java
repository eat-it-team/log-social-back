package ru.eatit.social_protection.integration.service.extract.normalizer;

import org.springframework.stereotype.Service;
import ru.eatit.common.api.DataNormalizer;
import ru.eatit.common.entity.extract.NormalizeResult;
import ru.eatit.common.other.InnChecker;

@Service
public class InnDataNormalizer implements DataNormalizer {

    @Override
    public NormalizeResult normalize(String rawValue) {
        String data = rawValue.trim();
        boolean isValid = InnChecker.checkInn(data);
        if (isValid) {
            return new NormalizeResult(true, data, null);
        }
        return new NormalizeResult(false, data, "Ошибка при валидации ИНН");
    }
}
