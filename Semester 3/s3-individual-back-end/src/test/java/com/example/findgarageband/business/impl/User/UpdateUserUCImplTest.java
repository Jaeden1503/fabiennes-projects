package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.business.exception.InvalidIdException;
import com.example.findgarageband.business.exception.UnauthorizedDataAccessException;
import com.example.findgarageband.business.exception.UsernameAlreadyExistsException;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.domain.User.UpdateUserRequest;
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

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserUCImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private AccessToken accessToken;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    UpdateUserUCImpl updateUserUC;

    @Test
    void updateUser_shouldUpdateAllUserFields() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .password("new_password")
                .about("about the user")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .password("new_password")
                .about("about the user")
                .build();

        userEntity.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .id(1L)
                        .user(userEntity)
                        .role(RoleEnum.USER)
                        .build()
        ));

        when(accessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(userRepositoryMock.findById(request.getId())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.encode(request.getPassword())).thenReturn(userEntity.getPassword());
        when(userRepositoryMock.save(userEntity)).thenReturn(userEntity);

        updateUserUC.updateUser(request);

        verify(userRepositoryMock).save(userEntity);
    }

    @Test
    void updateUser_shouldUpdateWithoutSendingPassword() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .password(null)
                .about("about the user")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .password("new_password")
                .about("about the user")
                .build();

        userEntity.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .id(1L)
                        .user(userEntity)
                        .role(RoleEnum.USER)
                        .build()
        ));

        when(accessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(userRepositoryMock.findById(request.getId())).thenReturn(Optional.of(userEntity));
//        when(passwordEncoder.encode(request.getPassword())).thenReturn(userEntity.getPassword());
        when(userRepositoryMock.save(userEntity)).thenReturn(userEntity);

        updateUserUC.updateUser(request);

        verify(userRepositoryMock).save(userEntity);
    }

    @Test
    void updateUser_shouldNotUpdateAllUserFields() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .password("new_password")
                .about("about the user")
                .build();

        when(accessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);

        assertThrows(UnauthorizedDataAccessException.class, () -> {
            updateUserUC.updateUser(request);
        });
    }

    @Test
    void updateUser_shouldNotUpdateAllUserFieldsBecauseDuplicateUsername() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .password("new_password")
                .about("about the user")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("cool_nickname")
                .email("fabienne@gmail.com")
                .password("new_password")
                .about("about the user")
                .build();

        userEntity.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .id(1L)
                        .user(userEntity)
                        .role(RoleEnum.USER)
                        .build()
        ));

        when(accessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(userRepositoryMock.findById(request.getId())).thenReturn(Optional.of(userEntity));
        when(userRepositoryMock.existsByUsername(request.getUsername())).thenReturn(true);

        assertThrows(UsernameAlreadyExistsException.class, () -> {
            updateUserUC.updateUser(request);
        });
    }

    @Test
    void updateUser_shouldNotUpdateAllUserFieldsBecauseEmpty() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .password("new_password")
                .about("about the user")
                .build();

        when(accessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(userRepositoryMock.findById(request.getId())).thenReturn(Optional.empty());

        assertThrows(InvalidIdException.class, () -> {
            updateUserUC.updateUser(request);
        });
    }
}