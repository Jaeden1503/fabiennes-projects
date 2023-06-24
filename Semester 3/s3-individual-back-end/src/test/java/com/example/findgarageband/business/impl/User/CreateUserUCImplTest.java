package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.business.exception.UsernameAlreadyExistsException;
import com.example.findgarageband.domain.User.CreateUserRequest;
import com.example.findgarageband.domain.User.CreateUserResponse;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.RoleEnum;
import com.example.findgarageband.persistence.entity.UserEntity;
import com.example.findgarageband.persistence.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUCImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateUserUCImpl createUserUC;

    @Test
    void createUser_shouldSaveUserConverted() {
        UserEntity newUser = UserEntity.builder()
                .username("cool_user")
                .email("test@gmail.com")
                .password("very_secure_password")
                .build();

        newUser.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(newUser)
                        .role(RoleEnum.USER)
                        .build()
        ));


        UserEntity returnedUser = UserEntity.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .password("very_secure_password")
                .build();

        returnedUser.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .id(1L)
                        .user(returnedUser)
                        .role(RoleEnum.USER)
                        .build()
        ));

        when(userRepositoryMock.save(newUser)).thenReturn(returnedUser);
        when(passwordEncoder.encode(newUser.getPassword())).thenReturn(returnedUser.getPassword());

        CreateUserRequest request = CreateUserRequest.builder()
                .username("cool_user")
                .email("test@gmail.com")
                .password("very_secure_password")
                .build();

        CreateUserResponse actualResponse = createUserUC.createUser(request);

        CreateUserResponse expectedResponse = CreateUserResponse.builder()
                .userId(1L)
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(userRepositoryMock).save(newUser);
    }

    @Test
    void createUser_shouldNotSaveUser() {
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username("fabienne")
                .email("fabienne@gmail.com")
                .password("very_secure_password")
                .build();

        when(userRepositoryMock.existsByUsername(userRequest.getUsername())).thenReturn(true);

        assertThrows(UsernameAlreadyExistsException.class, () -> {
            createUserUC.createUser(userRequest);
        });
    }
}
