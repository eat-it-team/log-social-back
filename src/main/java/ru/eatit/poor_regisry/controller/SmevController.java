package ru.eatit.poor_regisry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.eatit.poor_regisry.controller.dto.SmevDto;
import ru.eatit.poor_regisry.service.internal.SmevService;

import static ru.eatit.poor_regisry.controller.api.Urls.SMEV_URL;

@RestController
@RequiredArgsConstructor
public class SmevController {
    private final SmevService service;

    @PostMapping("/extract")
    public SmevDto extract(@RequestBody String id) {
        return service.extractFormSmevAndGet(id);
    }


}
