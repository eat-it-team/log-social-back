package ru.eatit.poor_regisry.service.internal;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.controller.dto.SubsidyUserDto;
import ru.eatit.poor_regisry.controller.dto.UserSubsidyDto;
import ru.eatit.poor_regisry.repository.mongo.entity.Subsidy;
import ru.eatit.poor_regisry.repository.mongo.entity.User;
import ru.eatit.poor_regisry.service.mapper.SubsidyMapper;
import ru.eatit.poor_regisry.service.mapper.UserMapper;
import ru.eatit.poor_regisry.service.provider.SubsidyProvider;

@Service
@RequiredArgsConstructor
public class SubsidyContainer {
    private final List<SubsidyProvider> listOfSubsidies;
    private final MongoTemplate mongoTemplate;
    private final SubsidyMapper subsidyMapper;
    private final UserMapper userMapper;

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

    public List<UserSubsidyDto> getUserSubsidyMap(List<User> users) {
        return users.stream()
                .map(user -> {
                    List<SubsidyDto> subsidyDtos = getForUser(user);
                    return UserSubsidyDto.builder()
                            .subsidy(subsidyDtos)
                            .user(userMapper.toDto(user, null))
                            .build();
                }).collect(Collectors.toList());
    }

    public List<SubsidyUserDto> getSubsidyUserMap(List<User> users) {
        return listOfSubsidies.stream()
                .map(subsidyProvider ->
                    SubsidyUserDto.builder()
                            .subsidyDto(subsidyProvider.get())
                            .userDtoList(users.stream()
                                    .filter(subsidyProvider::isApplied)
                                    .map(user -> userMapper.toDto(user, null))
                                    .collect(Collectors.toList()))
                            .build())
                .collect(Collectors.toList());
    }
}
