package ru.eatit.poor_regisry.controller.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserSubsidyDto {
    private UserDto user;
    private List<SubsidyDto> subsidy;
}
