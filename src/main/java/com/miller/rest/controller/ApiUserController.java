package com.miller.rest.controller;

import com.miller.domain.entity.ApiUser;
import com.miller.rest.dto.ApiUserDTO;
import com.miller.service.implementation.UserServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    private final UserServiceImplementation userServiceImplementation;

    public ApiUserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiUserDTO save(@RequestBody @Valid ApiUser apiUser) {
        return userServiceImplementation.save(apiUser);
    }
}
