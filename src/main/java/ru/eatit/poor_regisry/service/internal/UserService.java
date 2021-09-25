package ru.eatit.poor_regisry.service.internal;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.eatit.common.entity.extract.ExtractResult;
import ru.eatit.integration.service.smev.domain.GetAllDataRequest;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;
import ru.eatit.integration.service.smev.service.GetAllDataExtractor;
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
    private final GetAllDataExtractor extractor;
    private final GetAllDataServiceClient userDataClient;
    private final UserMapper userMapper;
    private final SubsidyMapper subsidyMapper;

    public UserDto registerUser(String id) {
        if (Objects.isNull(id) || id.isEmpty()) {
            return registerUserFromSmev("1");
        }
        User user = mongoTemplate.findById(id, User.class);
        if (user != null) {
            return userMapper.toDto(user, findSubsidy(user));
        }
        return registerUserFromSmev(id);
    }

    private UserDto registerUserFromSmev(String id) {
        GetAllDataRequest request = new GetAllDataRequest(id);
        GetAllDataResponse response = userDataClient.getData(new GetAllDataRequest(id));
        ExtractResult extractResult = extractor.extract("smev", request, response);

        User update = update(extractResult.getId(), extractResult.getDetails());
        findSubsidy(update);
        return userMapper.toDto(update, findSubsidy(update));
    }

    private SubsidyDto findSubsidy(User update) {
        String subsidyId = (String) update.getDetails().get("subsidy");
        return subsidyId == null ? null : subsidyMapper.toDto(
                Objects.requireNonNull(mongoTemplate.findById(subsidyId, Subsidy.class)));
    }

    private User update(String id, JSONObject newData) {
        User byId = mongoTemplate.findById(id, User.class);
        if (byId != null && byId.getDetails() != null) {
            for (Object s : newData.keySet()) {
                byId.getDetails().put(s, newData.get(s));
            }
            return mongoTemplate.save(byId);
        } else {
            return mongoTemplate.save(new User(id, newData));
        }
    }

    public List<User> getAll() {
        return mongoTemplate.findAll(User.class);
    }


}
