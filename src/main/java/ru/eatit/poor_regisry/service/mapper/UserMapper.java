package ru.eatit.poor_regisry.service.mapper;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;
import ru.eatit.poor_regisry.controller.dto.UserDto;
import ru.eatit.poor_regisry.repository.mongo.entity.User;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
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
                .build();
    }

    public UserDto toDto(GetAllDataResponse data) {
        return UserDto.builder()
                .id(data.getId())
                .worker(!data.getПризнакНаличияСтатусаБезработного())
                .snills(data.getSnils())
                .poverty(data.getПризнакМалоимущести())
                .pensioner(data.getПризнакПенсионера())
                .lastName(data.getLastName())
                .secondName(data.getMiddleName())
                .firstName(data.getFirstName())
                .build();
    }
}
