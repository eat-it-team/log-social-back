package ru.eatit.poor_regisry.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.eatit.poor_regisry.controller.api.Urls;
import ru.eatit.poor_regisry.controller.dto.SubsidyDto;
import ru.eatit.poor_regisry.controller.dto.SubsidyRequest;
import ru.eatit.poor_regisry.controller.dto.SubsidyUserDto;
import ru.eatit.poor_regisry.controller.dto.UserSubsidyDto;
import ru.eatit.poor_regisry.service.PoorRegistryService;

@RestController
@RequestMapping(Urls.POOR_REGISTRY_URL)
@RequiredArgsConstructor
public class PoorRegistryController {

    private final PoorRegistryService poorRegistryService;

    @GetMapping("/all")
    @CrossOrigin
    public List<SubsidyDto> getAll() {
        return poorRegistryService.getAllSubsidies();
    }

    @GetMapping
    @CrossOrigin
    public List<SubsidyDto> get(@RequestParam("userId") String userId) {
        return poorRegistryService.getForUser(userId);
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity getSubsidy(@RequestBody SubsidyRequest request) {
        if (poorRegistryService.registerSubsidy(request.getUserId(), request.getSubsidyId())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/user-subsidy-map")
    @CrossOrigin
    public List<UserSubsidyDto> getUserSubsidyMap() {
        return poorRegistryService.getUserSubsidyMap();
    }

    @GetMapping("/subsidy-user-map")
    @CrossOrigin
    public List<SubsidyUserDto> getSubsidyUserMap() {
        return poorRegistryService.getSubsidyUserMap();
    }

}
