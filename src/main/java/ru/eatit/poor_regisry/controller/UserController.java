package ru.eatit.poor_regisry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.eatit.poor_regisry.controller.dto.UserDto;
import ru.eatit.poor_regisry.service.internal.UserService;

import static ru.eatit.poor_regisry.controller.api.Urls.USER_URL;

@RestController(USER_URL)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody String id) {
        return userService.registerUser(id);
    }

}
