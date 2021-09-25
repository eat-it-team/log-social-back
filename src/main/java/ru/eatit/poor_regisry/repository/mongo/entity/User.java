package ru.eatit.poor_regisry.repository.mongo.entity;


import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Сущность клиента содержит JSONObject details, которое позволяет хранить переменное число объектов
 * В качестве id скорее всего будет СНИЛС
 */
@Document
public class User {

    @Id
    private String id;

    private JSONObject details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JSONObject getDetails() {
        return details;
    }

    public void setDetails(JSONObject details) {
        this.details = details;
    }
}
