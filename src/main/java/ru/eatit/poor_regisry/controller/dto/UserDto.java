package ru.eatit.poor_regisry.controller.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"id", "snills"})
public class UserDto {
    private String id;
    private String snills;
    private String firstName;
    private String secondName;
    private String lastName;
    private String address;
    private String gender;

    private boolean poverty;
    private boolean pensioner;
    private boolean worker;
    private Boolean married;
    private Boolean haveChild;
    private SubsidyDto subsidyDto;
}
