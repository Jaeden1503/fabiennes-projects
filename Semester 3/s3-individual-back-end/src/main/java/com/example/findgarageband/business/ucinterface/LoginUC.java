package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.LoginRequest;
import com.example.findgarageband.domain.LoginResponse;

public interface LoginUC {
    LoginResponse login(LoginRequest loginRequest);
}
