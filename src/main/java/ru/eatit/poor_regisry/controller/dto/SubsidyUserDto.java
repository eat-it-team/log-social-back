package ru.eatit.poor_regisry.controller.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubsidyUserDto {
    private SubsidyDto subsidyDto;
    private List<UserDto> userDtoList;
}
