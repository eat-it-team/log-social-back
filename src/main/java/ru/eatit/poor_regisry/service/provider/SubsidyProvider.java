package ru.eatit.poor_regisry.service.provider;

import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.repository.mongo.entity.User;

public interface SubsidyProvider {

    SubsidyDto get();

    boolean isApplied(User user);
}
