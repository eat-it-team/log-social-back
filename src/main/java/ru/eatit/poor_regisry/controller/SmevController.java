package ru.eatit.poor_regisry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.eatit.poor_regisry.controller.dto.SmevDto;
import ru.eatit.poor_regisry.repository.mongo.entity.User;
import ru.eatit.poor_regisry.service.PoorRegistryService;
import ru.eatit.poor_regisry.service.internal.SmevService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SmevController {
    private final SmevService service;
    private final PoorRegistryService poorRegistryService;

    @PostMapping("smev/poor_registry/extract")
    public SmevDto extract(@RequestBody String id) {
        return service.extractFormSmevAndGet(id);
    }

    @GetMapping("smev/poor_registry/all")
    public List<User> getAll() {
        return poorRegistryService.getAll();
    }


}
