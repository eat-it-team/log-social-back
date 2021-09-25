package ru.eatit.poor_regisry.service.mapper;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;
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
                .pensioner((Boolean) details.get("pensioner"))
                .poverty((Boolean) details.get("poverty"))
                .snills((String) details.get("snills"))
                .worker((Boolean) details.get("worker"))
                .address((String) details.get("address"))
                .subsidyDto(subsidyDto)
                .build();
    }

    public User toEntity(GetAllDataResponse userData) {
        User user = new User();
        user.setId(userData.getId());
        JSONObject details = new JSONObject();
        details.put("firstName", userData.getFirstName());
        details.put("secondName", userData.getMiddleName());
        details.put("lastName", userData.getLastName());
        details.put("pensioner", userData.getПризнакПенсионера());
        details.put("poverty", userData.getПризнакМалоимущести());
        details.put("snills", userData.getSnils());
        details.put("worker", userData.getПризнакНаличияСтатусаБезработного());
        details.put("address", userData.getAddress());
        details.put("gender", userData.getGender());
        details.put("have_child", userData.getПризнакНаличияНесовершеннолетнегоРебенка());
        details.put("married", userData.getПризнакЖенатостиЗамужнести());
        user.setDetails(details);
        return user;
    }
}
