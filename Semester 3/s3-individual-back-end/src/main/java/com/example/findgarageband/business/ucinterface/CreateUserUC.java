package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.User.CreateUserRequest;
import com.example.findgarageband.domain.User.CreateUserResponse;

public interface CreateUserUC {
    CreateUserResponse createUser(CreateUserRequest request);
}
