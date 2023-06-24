package com.example.findgarageband.repository;

import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
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

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SearchPostRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SearchPostRepository searchPostRepository;

    @Test
    void save_shouldSaveSearchPostWithAllFields() {
        LocalDate date = LocalDate.of(2020, 1, 8);

        UserEntity user = UserEntity.builder().username("username").email("email@gmail.com").password("very_secure_password").build();
        entityManager.persist(user);

        SearchPostEntity searchPostEntity = SearchPostEntity.builder()
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .userEntity(user)
                .build();
        entityManager.persist(searchPostEntity);

        SearchPostEntity savedSearchPost = searchPostRepository.save(searchPostEntity);
        assertNotNull(savedSearchPost.getId());
        savedSearchPost = entityManager.find(SearchPostEntity.class, savedSearchPost.getId());

        SearchPostEntity expectedSearchPostEntity = SearchPostEntity.builder()
                .id(savedSearchPost.getId())
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .userEntity(user)
                .build();

        assertEquals(expectedSearchPostEntity, savedSearchPost);
    }
}
