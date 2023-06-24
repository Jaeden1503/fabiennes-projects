package com.example.findgarageband.business.impl;

import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.business.exception.UnauthorizedDataAccessException;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.persistence.entity.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccessTokenValidatorImpl implements AccessTokenValidator {
    private AccessToken requestAccessToken;

    @Override
    public void checkAccessToken(Long userId) {
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name())) {
            if (!requestAccessToken.getUserId().equals(userId)) {
                throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }
    }

    @Override
    public void checkUserOnlyAccessToken(Long userId) {
        if (!requestAccessToken.hasRole(RoleEnum.USER.name()) && !requestAccessToken.getUserId().equals(userId)) {
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }
    }
}
