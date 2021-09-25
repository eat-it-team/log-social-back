package ru.eatit.poor_regisry.service.mapper;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Component;
import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.controller.dto.UserDto;
import ru.eatit.poor_regisry.repository.mongo.entity.User;

@Component
public class UserMapper {

    public UserDto toDto(User user, SubsidyDto subsidyDto) {
        JSONObject details = user.getDetails();
        return UserDto.builder()
                .id(user.getId())
                .firstName((String) details.get("firstName"))
                .secondName((String) details.get("secondName"))
                .lastName((String) details.get("lastName"))
                .pensioner(Boolean.parseBoolean((String) details.get("pensioner")))
                .poverty(Boolean.parseBoolean((String) details.get("poverty")))
                .snills((String) details.get("snills"))
                .worker(Boolean.parseBoolean((String) details.get("worker")))
                .address((String) details.get("address"))
                .gender((String) details.get("gender"))
                .lat(getLat(details))
                .lon(getLon(details))
                .subsidyDto(subsidyDto)
                .build();
    }

    private double getLat(JSONObject details) {
        return (Double) ((JSONObject) JSONValue.parse((String) details.get("geoAddress"))).get("geoLat");
    }

    private double getLon(JSONObject details) {
        return (Double) ((JSONObject) JSONValue.parse((String) details.get("geoAddress"))).get("geoLon");
    }
}
