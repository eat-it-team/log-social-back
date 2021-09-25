package ru.eatit.poor_regisry.service.internal;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.repository.mongo.entity.Subsidy;
import ru.eatit.poor_regisry.repository.mongo.entity.User;
import ru.eatit.poor_regisry.service.mapper.SubsidyMapper;
import ru.eatit.poor_regisry.service.provider.SubsidyProvider;

@Service
@RequiredArgsConstructor
public class SubsidyContainer {
    private final List<SubsidyProvider> listOfSubsidies;
    private final MongoTemplate mongoTemplate;
    private final SubsidyMapper subsidyMapper;

    @PostConstruct
    public void init() {
        listOfSubsidies.forEach(subsidyProvider -> {
            SubsidyDto dto = subsidyProvider.get();
            Subsidy subsidy = mongoTemplate.findById(dto.getId(), Subsidy.class);
            if (subsidy == null) {
                mongoTemplate.save(subsidyMapper.toEntity(dto));
            }
        });
    }

    public List<SubsidyDto> getForUser(User user) {
        return listOfSubsidies.stream()
                .filter(subsidyProvider -> subsidyProvider.isApplied(user))
                .map(SubsidyProvider::get)
                .collect(Collectors.toList());
    }

    public boolean registerSubsidy(User user, String subsidyId) {
        Optional<SubsidyProvider> provider = listOfSubsidies.stream()
                .filter(subsidyProvider -> subsidyProvider.get().getId().equals(subsidyId))
                .filter(subsidyProvider -> subsidyProvider.isApplied(user))
                .findFirst();
        if (provider.isPresent()) {
            JSONObject details = user.getDetails();
            details.put("subsidy", provider.get().get().getId());
            mongoTemplate.save(user);
            return true;
        }
        return false;
    }
}
