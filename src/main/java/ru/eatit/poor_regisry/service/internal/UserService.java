package ru.eatit.poor_regisry.service.internal;

import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.eatit.integration.service.smev.domain.GetAllDataRequest;
import ru.eatit.integration.service.smev.service.GetAllDataServiceClient;
import ru.eatit.poor_regisry.controller.dto.UserDto;
import ru.eatit.poor_regisry.repository.mongo.entity.User;
import ru.eatit.poor_regisry.service.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MongoTemplate mongoTemplate;
    private final GetAllDataServiceClient userDataClient;
    private final UserMapper userMapper;

    public UserDto registerUser(String id) {
        if (Objects.isNull(id) || id.isBlank()) {
            return registerUserFromSmev(id);
        }
        var user = Optional.ofNullable(mongoTemplate.findById(id, User.class));
        if (user.isPresent()) {
            return userMapper.toDto(user.get());
        }
        return registerUserFromSmev(id);
    }

    private UserDto registerUserFromSmev(String id) {
        var userData = userDataClient.getData(new GetAllDataRequest(id));
        return userMapper.toDto(mongoTemplate.save(userMapper.toEntity(userData)));
    }


}
