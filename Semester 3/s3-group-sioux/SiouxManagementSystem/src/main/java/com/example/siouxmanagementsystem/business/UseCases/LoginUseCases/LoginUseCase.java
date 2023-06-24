package com.example.siouxmanagementsystem.business.UseCases.LoginUseCases;

import com.example.siouxmanagementsystem.domain.Login.LoginRequest;
import com.example.siouxmanagementsystem.domain.Login.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
