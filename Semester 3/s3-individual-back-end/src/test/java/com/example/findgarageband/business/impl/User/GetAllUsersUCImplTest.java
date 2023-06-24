package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.domain.User.GetAllUsersResponse;
import com.example.findgarageband.domain.User.User;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllUsersUCImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private GetAllUsersUCImpl getAllUsersUC;

    @Test
    void getAllUsers_shouldReturnAllUsersConverted() {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(UserEntity.builder()
                .id(1L)
                .username("cool_user")
                .email("cool@gmail.com")
                .about("about the user")
                .build());

        userList.add(UserEntity.builder()
                .id(2L)
                .username("test_user")
                .email("test@gmail.com")
                .about("about the other user")
                .build());

        when(userRepositoryMock.findAllNormalUsers()).thenReturn(userList);
        GetAllUsersResponse actualResult = getAllUsersUC.getAllUsers();

        User user1 = User.builder()
                .id(1L)
                .username("cool_user")
                .email("cool@gmail.com")
                .about("about the user")
                .build();

        User user2 = User.builder()
                .id(2L)
                .username("test_user")
                .email("test@gmail.com")
                .about("about the other user")
                .build();

        GetAllUsersResponse expectedResult = GetAllUsersResponse.builder()
                .users(List.of(user1, user2))
                .build();

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult.getUsers().size(), actualResult.getUsers().size());
        verify(userRepositoryMock).findAllNormalUsers();
    }
}