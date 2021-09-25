package ru.eatit.integration.service.extract.normalizer;

import org.springframework.stereotype.Service;
import ru.eatit.common.api.DataNormalizer;
import ru.eatit.common.entity.extract.NormalizeResult;

@Service
public class BooleanDataNormalizer implements DataNormalizer<Boolean> {

    @Override
    public NormalizeResult normalize(Boolean rawValue) {

        if (rawValue == null) {
            return new NormalizeResult(false, null, "Boolean не должен быть null или пустой");
        }


        return new NormalizeResult(true, Boolean.toString(rawValue), null);
    }
}
