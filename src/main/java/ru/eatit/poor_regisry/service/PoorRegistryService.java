package ru.eatit.poor_regisry.service;


import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.controller.dto.SubsidyUserDto;
import ru.eatit.poor_regisry.controller.dto.UserSubsidyDto;
import ru.eatit.poor_regisry.repository.mongo.entity.Subsidy;
import ru.eatit.poor_regisry.repository.mongo.entity.User;

import java.util.List;
import ru.eatit.poor_regisry.service.internal.SubsidyContainer;
import ru.eatit.poor_regisry.service.mapper.SubsidyMapper;

/**
 * Сервис для работы с сущностью клиента.
 * Будет содержать методы для поиска клиентов/обновления его полей
 */
@Service
@RequiredArgsConstructor
public class PoorRegistryService {

    private final MongoTemplate mongoTemplate;
    private final SubsidyMapper subsidyMapper;
    private final SubsidyContainer subsidyContainer;


    public User update(String id, JSONObject newData) {
        User byId = mongoTemplate.findById(id, User.class);
        if (byId != null && byId.getDetails() != null) {
            for (Object s : newData.keySet()) {
                byId.getDetails().put(s, newData.get(s));
            }
            return mongoTemplate.save(byId);
        } else {
            return mongoTemplate.save(new User(id, newData));
        }
    }

    public List<User> getAll() {
        return mongoTemplate.findAll(User.class);
    }

    public List<SubsidyDto> getAllSubsidies() {
        return subsidyMapper.toDto(mongoTemplate.findAll(Subsidy.class));
    }

    public List<SubsidyDto> getForUser(String userId) {
        User user = mongoTemplate.findById(userId, User.class);
        return subsidyContainer.getForUser(user);
    }

    public boolean registerSubsidy(String userId, String subsidyId) {
        User user = mongoTemplate.findById(userId, User.class);
        return subsidyContainer.registerSubsidy(user, subsidyId);
    }

    public List<UserSubsidyDto> getUserSubsidyMap() {
        List<User> users = mongoTemplate.findAll(User.class);
        return subsidyContainer.getUserSubsidyMap(users);
    }

    public List<SubsidyUserDto> getSubsidyUserMap() {
        List<User> users = mongoTemplate.findAll(User.class);
        return subsidyContainer.getSubsidyUserMap(users);
    }
}
