package com.miller.rest.controller;

import com.miller.domain.entity.ApiUser;
import com.miller.exceptions.InvalidPasswordException;
import com.miller.exceptions.UserNameNotFoundException;
import com.miller.rest.dto.ApiUserDTO;
import com.miller.rest.dto.CredentialsDTO;
import com.miller.rest.dto.TokenDTO;
import com.miller.security.jwt.JwtService;
import com.miller.service.implementation.UserServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    private final UserServiceImplementation userServiceImplementation;
    private final JwtService jwtService;

    public ApiUserController(UserServiceImplementation userServiceImplementation, JwtService jwtService) {
        this.userServiceImplementation = userServiceImplementation;
        this.jwtService = jwtService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiUserDTO save(@RequestBody @Valid ApiUser apiUser) {
        return userServiceImplementation.save(apiUser);
    }

    @PostMapping("/auth")
    public TokenDTO authentication(@RequestBody CredentialsDTO credentials) {
        try {

            ApiUser apiUser = ApiUser
                    .builder()
                    .username(credentials.getUsername())
                    .password(credentials.getPassword())
                    .build();

            userServiceImplementation.authentication(apiUser);

            String token = jwtService.generateToken(apiUser);

            return new TokenDTO(apiUser.getUsername(), token);
        } catch (UserNameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
