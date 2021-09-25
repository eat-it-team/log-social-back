package ru.eatit.poor_regisry.service;


import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.eatit.poor_regisry.repository.mongo.entity.User;

import javax.annotation.PostConstruct;
import java.util.List;

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

    @PostConstruct
    public void init() {

        //Демонстрация работы

        User user = new User();
        user.setId("1");
        JSONObject obj = new JSONObject();
        obj.put("key", "value");

        user.setDetails(obj);
        mongoTemplate.save(user);


        User user2 = new User();
        user2.setId("2");
        JSONObject obj2 = new JSONObject();
        obj2.put("key", "value");
        user2.setDetails(obj2);
        mongoTemplate.save(user2);

        User user3 = new User();
        user2.setId("3");
        JSONObject obj3 = new JSONObject();
        obj3.put("key", "value3");
        user3.setDetails(obj3);
        mongoTemplate.save(user3);

        User byId = mongoTemplate.findById("1", User.class);
        System.out.println(byId);

        Query query = new Query();
        query.addCriteria(Criteria.where("details.key").is("value"));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }


}
