package com.example.findgarageband.business.impl.Comment;

import com.example.findgarageband.business.exception.InvalidIdException;
import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.persistence.CommentRepository;
import com.example.findgarageband.persistence.entity.CommentEntity;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import com.example.findgarageband.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteCommentUCImplTest {
    @Mock
    private CommentRepository commentRepositoryMock;
    @Mock
    private AccessTokenValidator accessTokenValidator;
    @InjectMocks
    private DeleteCommentUCImpl deleteCommentUC;

    @Test
    void deleteComment_shouldDeleteComment() {
        LocalDate date = LocalDate.of(2020, 1, 8);

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .build();

        SearchPostEntity searchPostEntity = SearchPostEntity.builder()
                .id(1L)
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .userEntity(userEntity)
                .build();

        CommentEntity commentEntity = CommentEntity.builder()
                .id(1L)
                .description("hello")
                .date(date)
                .userEntity(userEntity)
                .searchPostEntity(searchPostEntity)
                .build();

        when(commentRepositoryMock.existsById(commentEntity.getId())).thenReturn(true);
        when(commentRepositoryMock.findCommentEntityById(commentEntity.getId())).thenReturn(commentEntity);
        doNothing().when(commentRepositoryMock).deleteById(commentEntity.getId());

        deleteCommentUC.deleteComment(commentEntity.getId());

        verify(commentRepositoryMock).deleteById(commentEntity.getId());
    }

    @Test
    void deleteComment_shouldNotDeleteComment() {
        when(commentRepositoryMock.existsById(1L)).thenReturn(false);

        assertThrows(InvalidIdException.class, () -> {
            deleteCommentUC.deleteComment(1L);
        });
    }
}