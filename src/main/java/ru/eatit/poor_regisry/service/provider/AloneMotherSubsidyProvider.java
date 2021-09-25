package ru.eatit.poor_regisry.service.provider;

import java.util.Objects;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.repository.mongo.entity.User;

@Component
public class AloneMotherSubsidyProvider implements SubsidyProvider {
    private static final JSONObject details = new JSONObject();

    {
        details.put("Женщина", true);
        details.put("Несовершеннолетние дети", true);
        details.put("Выплата", 444);
    }

    @Override
    public SubsidyDto get() {
        return SubsidyDto.builder()
                .id("alone_mother")
                .period("Every month")
                .description("Пособие при наличии детей в случае, если заявитель - одинокая мать")
                .details(details)
                .build();
    }

    @Override
    public boolean isApplied(User user) {
        JSONObject details = user.getDetails();
        return Objects.equals(details.get("gender"), "woman") &&
                Objects.equals(details.get("haveChild"), true) &&
                Objects.equals(details.get("married"), false);
    }
}
