package ru.eatit.poor_regisry.controller.dto;

import org.json.simple.JSONObject;
import ru.eatit.poor_regisry.repository.mongo.entity.User;

public class SmevDto extends User {
    public SmevDto(String id, JSONObject details) {
        super(id, details);
    }
}
