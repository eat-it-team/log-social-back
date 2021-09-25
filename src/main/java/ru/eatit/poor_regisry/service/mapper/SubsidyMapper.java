package ru.eatit.poor_regisry.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.repository.mongo.entity.Subsidy;

@Component
public class SubsidyMapper {

    public List<SubsidyDto> toDto(List<Subsidy> subsidies) {
        return subsidies.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public SubsidyDto toDto(Subsidy subsidy) {
        return SubsidyDto.builder()
                .id(subsidy.getId())
                .description(subsidy.getDescription())
                .period(subsidy.getPeriod())
                .details(subsidy.getDetails())
                .build();
    }

    public Subsidy toEntity(SubsidyDto dto) {
        return Subsidy.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .period(dto.getPeriod())
                .details(dto.getDetails())
                .build();
    }
}
