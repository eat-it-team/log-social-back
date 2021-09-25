package ru.eatit.poor_regisry.service;


import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.eatit.poor_regisry.repository.mongo.entity.User;

/**
 * Сервис для работы с сущностью клиента.
 * Будет содержать методы для поиска клиентов/обновления его полей
 */
@Service
public class PoorRegistryService {

    private final MongoTemplate mongoTemplate;

    public PoorRegistryService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public User update(String id, JSONObject newData) {
        User byId = mongoTemplate.findById(id, User.class);
        if (byId != null && byId.getDetails() != null) {
            for (Object s : newData.keySet()) {
                byId.getDetails().put(s, newData.get(s));
            }
        }
        return mongoTemplate.save(byId);
    }

}
