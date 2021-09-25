package ru.eatit.poor_regisry.service.provider;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.repository.mongo.entity.User;

@Component
public class PovertySubsidyProvider implements SubsidyProvider {
    private static final JSONObject details = new JSONObject();

    {
        details.put("Пенсионер", 10022);
        details.put("Ребенок", 11303);
        details.put("Рабочий", 12702);
        details.put("Выплата", 2676.3);
    }

    @Override
    public SubsidyDto get() {
        return SubsidyDto.builder()
                .id("poverty_citizen")
                .description("Государственная социальная помощь малоимущим гражданам и гражданам, оказавшимся в трудной жизненной ситуации")
                .period("Once")
                .details(details)
                .build();
    }

    @Override
    public boolean isApplied(User user) {
        return (Boolean) user.getDetails().getOrDefault("poverty", false);
    }
}
