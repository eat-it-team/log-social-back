package ru.eatit.poor_regisry.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubsidyRequest {
    private String userId;
    private String subsidyId;
}
