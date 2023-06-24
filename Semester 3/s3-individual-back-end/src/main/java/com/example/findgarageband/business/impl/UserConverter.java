package com.example.findgarageband.business.impl;

import com.example.findgarageband.domain.User.User;
import com.example.findgarageband.persistence.entity.UserEntity;

public final class UserConverter {
    private UserConverter() {

    }

    //converts from user entity to user
    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .about(user.getAbout())
                .build();
    }

    //converts from user to user entity
    public static UserEntity convert(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .about(user.getAbout())
                .build();
    }
}
