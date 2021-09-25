package ru.eatit.social_protection.integration.outside.domain.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"snills"})
@Builder
public class PersonDto {
    private String snills;
    private String firstName;
    private String secondName;
    private String lastName;
    private String inn;
    private LocalDate birthDate;
    private boolean male;
}
