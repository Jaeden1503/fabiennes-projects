package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.business.exception.InvalidIdException;
import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.domain.User.User;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserUCImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private AccessTokenValidator accessTokenValidator;
    @InjectMocks
    private GetUserUCImpl getUserUC;

    @Test
    void getUser_shouldReturnUserConverted() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .about("about the user")
                .build();

        when(userRepositoryMock.existsById(1L)).thenReturn(true);
        when(userRepositoryMock.findUserEntityById(1L)).thenReturn(userEntity);

        User actualUser = getUserUC.getUser(1L);

        User expectedUser = User.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .about("about the user")
                .build();

        assertEquals(expectedUser, actualUser);
        verify(userRepositoryMock).existsById(1L);
        verify(userRepositoryMock).findUserEntityById(1L);
    }

    @Test
    void getUser_shouldReturnInvalidUserIdException() {
        when(userRepositoryMock.existsById(2L)).thenReturn(false);

        assertThrows(InvalidIdException.class, () -> {
            getUserUC.getUser(2L);
        });
    }
}