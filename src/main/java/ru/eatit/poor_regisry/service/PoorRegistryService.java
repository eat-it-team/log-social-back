package ru.eatit.poor_regisry.service;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

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

}
