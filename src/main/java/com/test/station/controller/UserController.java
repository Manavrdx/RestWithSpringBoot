package com.test.station.controller;

import com.test.station.entity.User;
import com.test.station.service.command.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserCommandService userCommandService;

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        userCommandService.registerUser(user);
    }

}
