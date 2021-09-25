package ru.eatit.poor_regisry.service.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.eatit.common.entity.extract.ExtractResult;
import ru.eatit.integration.service.smev.domain.GetAllDataRequest;
import ru.eatit.integration.service.smev.domain.GetAllDataResponse;
import ru.eatit.integration.service.smev.service.GetAllDataExtractor;
import ru.eatit.integration.service.smev.service.GetAllDataServiceClient;
import ru.eatit.poor_regisry.controller.dto.SmevDto;
import ru.eatit.poor_regisry.repository.mongo.entity.User;

@Service
@RequiredArgsConstructor
public class SmevService {

    private final GetAllDataExtractor extractor;
    private final GetAllDataServiceClient allDataServiceClient;
    private final MongoTemplate mongoTemplate;

    public SmevDto extractFormSmevAndGet(String userId) {
        GetAllDataRequest request = new GetAllDataRequest(userId);
        GetAllDataResponse response = allDataServiceClient.getData(new GetAllDataRequest(userId));
        ExtractResult extractResult = extractor.extract("smev", request, response);
        mongoTemplate.save(new User(extractResult.getId(), extractResult.getDetails()));
        User byId = mongoTemplate.findById(extractResult.getId(), User.class);
        return new SmevDto(byId.getId(), byId.getDetails());
    }

}
