package com.example.findgarageband.business.impl.User;

import com.example.findgarageband.business.impl.UserConverter;
import com.example.findgarageband.domain.User.User;
import com.example.findgarageband.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {

    @Test
    void shouldConvertAllUserFieldsToDomain() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .about("about the user")
                .build();

        User actualUser = UserConverter.convert(userEntity);

        User expectedUser = User.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .about("about the user")
                .build();

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void shouldConvertAllUserFieldsToEntity() {
        User user = User.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .about("about the user")
                .build();

        UserEntity actualUser = UserConverter.convert(user);

        UserEntity expectedUser = UserEntity.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .about("about the user")
                .build();

        assertEquals(expectedUser, actualUser);
    }
}
