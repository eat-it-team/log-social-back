package ru.eatit.poor_regisry.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Builder
@Getter
@Setter
public class SubsidyDto {
    private String id;
    private String description;
    private String period;
    private JSONObject details;
}
