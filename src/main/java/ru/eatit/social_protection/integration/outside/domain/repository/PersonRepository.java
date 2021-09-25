package ru.eatit.social_protection.integration.outside.domain.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;
import ru.eatit.social_protection.integration.outside.domain.dto.PersonDto;

@Component
public class PersonRepository {
    private static final Map<String, PersonDto> persons = Map.of(
            "111-111-111", PersonDto.builder()
                    .snills("111-111-111")
                    .firstName("Иван")
                    .secondName("Иванович")
                    .lastName("Иванов")
                    .birthDate(LocalDate.of(1990, Month.APRIL, 1))
                    .inn("111-222-333")
                    .male(true)
                    .build(),
            "222-222-222", PersonDto.builder()
                    .snills("222-222-222")
                    .lastName("Петрова")
                    .firstName("Таисия")
                    .secondName("Петровна")
                    .birthDate(LocalDate.of(1977, Month.OCTOBER, 13))
                    .inn("222-333-444")
                    .male(false)
                    .build()
    );

    public Optional<PersonDto> findById(String snills) {
        return Optional.ofNullable(persons.get(snills));
    }
}
