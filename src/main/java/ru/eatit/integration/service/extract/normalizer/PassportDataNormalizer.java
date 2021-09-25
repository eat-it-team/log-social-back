package ru.eatit.integration.service.extract.normalizer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.eatit.common.api.DataNormalizer;
import ru.eatit.common.entity.extract.NormalizeResult;
import ru.eatit.integration.service.smev.domain.Passport;

@Service
public class PassportDataNormalizer implements DataNormalizer<Passport> {

    @Override
    public NormalizeResult normalize(Passport rawValue) {
        if (rawValue == null) {
            return new NormalizeResult(false, null, "Пасспорт не должна быть null или пустой");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(rawValue);
            return new NormalizeResult(true, s, null);
        } catch (JsonProcessingException e) {
            return new NormalizeResult(true, null, e.getMessage());
        }
    }
}
