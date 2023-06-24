package com.example.findgarageband.repository;

import com.example.findgarageband.persistence.CommentRepository;
import com.example.findgarageband.persistence.entity.CommentEntity;
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
class CommentRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void save_shouldSaveCommentWithAllFields() {
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

        CommentEntity commentEntity = CommentEntity.builder()
                .description("this is a reply")
                .date(date)
                .userEntity(user)
                .searchPostEntity(searchPostEntity)
                .build();
        entityManager.persist(commentEntity);

        CommentEntity savedComment = commentRepository.save(commentEntity);
        assertNotNull(savedComment.getId());
        savedComment = entityManager.find(CommentEntity.class, commentEntity.getId());

        CommentEntity expectedComment = CommentEntity.builder()
                .id(savedComment.getId())
                .description("this is a reply")
                .date(date)
                .userEntity(user)
                .searchPostEntity(searchPostEntity)
                .build();

        assertEquals(expectedComment, savedComment);
    }
}
