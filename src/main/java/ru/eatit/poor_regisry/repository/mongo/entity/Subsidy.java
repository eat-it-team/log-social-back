package ru.eatit.poor_regisry.repository.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Subsidy {

    @Id
    private String id;

    private String description;

    private String period;

    private JSONObject details;
}
