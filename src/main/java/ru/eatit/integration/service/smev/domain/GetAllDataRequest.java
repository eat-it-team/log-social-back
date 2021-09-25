package ru.eatit.integration.service.smev.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Представление запроса через СМЭВ к сервису
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllDataRequest {

    String esiaUserId;
}
