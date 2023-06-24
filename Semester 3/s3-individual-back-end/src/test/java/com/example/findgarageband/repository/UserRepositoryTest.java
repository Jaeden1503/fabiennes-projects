package com.example.findgarageband.repository;

import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_shouldSaveUserWithAllFields() {
        UserEntity userEntity = UserEntity.builder()
                .username("cool_user")
                .email("test@gmail.com")
                .password("very_secure_password")
                .build();

        UserEntity savedUser = userRepository.save(userEntity);
        assertNotNull(savedUser.getId());

        savedUser = entityManager.find(UserEntity.class, savedUser.getId());

        UserEntity expectedUser = UserEntity.builder()
                .id(savedUser.getId())
                .username("cool_user")
                .email("test@gmail.com")
                .password("very_secure_password")
                .build();

        assertEquals(expectedUser, savedUser);
    }
}
