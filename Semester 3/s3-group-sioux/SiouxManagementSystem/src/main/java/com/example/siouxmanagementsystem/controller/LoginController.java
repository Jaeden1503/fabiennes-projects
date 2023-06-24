package com.example.siouxmanagementsystem.controller;

import com.example.siouxmanagementsystem.business.UseCases.LoginUseCases.LoginUseCase;
import com.example.siouxmanagementsystem.domain.Login.LoginRequest;
import com.example.siouxmanagementsystem.domain.Login.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class LoginController {
    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }
}
