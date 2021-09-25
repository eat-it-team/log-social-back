package ru.eatit.poor_regisry.service.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.eatit.integration.service.smev.domain.GetAllDataRequest;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;
import ru.eatit.integration.service.smev.service.GetAllDataServiceClient;
import ru.eatit.poor_regisry.controller.dto.UserDto;
import ru.eatit.poor_regisry.repository.mongo.entity.User;
import ru.eatit.poor_regisry.service.mapper.UserMapper;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MongoTemplate mongoTemplate;
    private final GetAllDataServiceClient userDataClient;
    private final UserMapper userMapper;

    public UserDto registerUser(String id) {
        if (Objects.isNull(id) || id.isEmpty()) {
            return registerUserFromSmev(id);
        }
        User user = mongoTemplate.findById(id, User.class);
        if (user != null) {
            return userMapper.toDto(user);
        }
        return registerUserFromSmev(id);
    }

    private UserDto registerUserFromSmev(String id) {
        GetAllDataResponse userData = userDataClient.getData(new GetAllDataRequest(id));
        return userMapper.toDto(mongoTemplate.save(userMapper.toEntity(userData)));
    }


}
