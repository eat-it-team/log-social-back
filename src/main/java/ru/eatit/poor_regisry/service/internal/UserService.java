package ru.eatit.poor_regisry.service.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.eatit.integration.service.smev.domain.GetAllDataRequest;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;
import ru.eatit.integration.service.smev.service.GetAllDataServiceClient;
import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.controller.dto.UserDto;
import ru.eatit.poor_regisry.repository.mongo.entity.Subsidy;
import ru.eatit.poor_regisry.repository.mongo.entity.User;
import ru.eatit.poor_regisry.service.mapper.SubsidyMapper;
import ru.eatit.poor_regisry.service.mapper.UserMapper;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MongoTemplate mongoTemplate;
    private final GetAllDataServiceClient userDataClient;
    private final UserMapper userMapper;
    private final SubsidyMapper subsidyMapper;

    public UserDto registerUser(String id) {
        if (Objects.isNull(id) || id.isEmpty()) {
            return registerUserFromSmev("1");
        }
        User user = mongoTemplate.findById(id, User.class);
        if (user != null) {
            String subsidyId = (String) user.getDetails().get("subsidy");
            SubsidyDto subsidyDto = subsidyId == null ? null : subsidyMapper.toDto(mongoTemplate.findById(subsidyId, Subsidy.class));
            return userMapper.toDto(user, subsidyDto);
        }
        return registerUserFromSmev(id);
    }

    private UserDto registerUserFromSmev(String id) {
        GetAllDataResponse userData = userDataClient.getData(new GetAllDataRequest(id));
        return userMapper.toDto(mongoTemplate.save(userMapper.toEntity(userData)), null);
    }


}
