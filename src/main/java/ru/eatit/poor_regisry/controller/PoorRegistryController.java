package ru.eatit.poor_regisry.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eatit.poor_regisry.controller.api.Urls;
import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.controller.dto.SubsidyRequest;
import ru.eatit.poor_regisry.service.PoorRegistryService;

@RestController
@RequestMapping(Urls.POOR_REGISTRY_URL)
@RequiredArgsConstructor
public class PoorRegistryController {

    private final PoorRegistryService poorRegistryService;

    @GetMapping("/all")
    public List<SubsidyDto> getAll() {
        return poorRegistryService.getAllSubsidies();
    }

    @GetMapping
    public List<SubsidyDto> get(@RequestParam("userId") String userId) {
        return poorRegistryService.getForUser(userId);
    }

    @PostMapping
    public ResponseEntity getSubsidy(@RequestBody SubsidyRequest request) {
        if (poorRegistryService.registerSubsidy(request.getUserId(), request.getSubsidyId())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
