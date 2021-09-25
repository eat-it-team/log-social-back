package ru.eatit.integration.service.extract.normalizer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.eatit.common.api.DataNormalizer;
import ru.eatit.common.entity.extract.NormalizeResult;
import ru.eatit.integration.service.dadata.entity.Geo;

@Service
public class GeoDataNormalizer implements DataNormalizer<Geo> {

    @Override
    public NormalizeResult normalize(Geo rawValue) {
        if (rawValue == null) {
            return new NormalizeResult(false, null, "GEO is null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(rawValue);
            return new NormalizeResult(true, s, null);
        } catch (JsonProcessingException e) {
            return new NormalizeResult(false, "unexpected", e.getMessage());
        }
    }
}
