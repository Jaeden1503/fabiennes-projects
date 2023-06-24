package com.example.findgarageband.business.impl;

import com.example.findgarageband.business.exception.UnauthorizedDataAccessException;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.persistence.entity.RoleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccessTokenValidatorImplTest {
    @Mock
    private AccessToken accessToken;
    @InjectMocks
    private AccessTokenValidatorImpl accessTokenValidator;

    @Test
    void validateAccessToken_shouldAccept() {
        when(accessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);

        accessTokenValidator.checkAccessToken(1L);
    }

    @Test
    void validateAccessToken_shouldNotAccept() {
        when(accessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);

        assertThrows(UnauthorizedDataAccessException.class, () -> {
            accessTokenValidator.checkAccessToken(1L);
        });
    }

    @Test
    void validateUserOnlyAccessToken_shouldAccept() {
        when(accessToken.hasRole(RoleEnum.USER.name())).thenReturn(true);
        accessTokenValidator.checkUserOnlyAccessToken(1L);
    }

    @Test
    void validateUserOnlyAccessToken_shouldNotAccept() {
        when(accessToken.hasRole(RoleEnum.USER.name())).thenReturn(false);

        assertThrows(UnauthorizedDataAccessException.class, () -> {
            accessTokenValidator.checkUserOnlyAccessToken(1L);
        });
    }
}