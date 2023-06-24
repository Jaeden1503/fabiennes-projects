package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.business.exception.InvalidSearchPostException;
import com.example.findgarageband.persistence.CommentRepository;
import com.example.findgarageband.persistence.SearchPostRepository;
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
class DeleteSearchPostUCImplTest {
    @Mock
    private SearchPostRepository searchPostRepositoryMock;
    @Mock
    private CommentRepository commentRepositoryMock;
    @Mock
    private AccessTokenValidator accessTokenValidator;
    @InjectMocks
    private DeleteSearchPostUCImpl deleteSearchPostUC;

    @Test
    void deleteSearchPostById_shouldDeleteSearchPost() {
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

        when(searchPostRepositoryMock.existsById(1L)).thenReturn(true);
        when(searchPostRepositoryMock.findSearchPostEntityById(1L)).thenReturn(searchPostEntity);
        doNothing().when(commentRepositoryMock).deleteCommentsBySearchPostId(1L);
        doNothing().when(searchPostRepositoryMock).deleteById(1L);

        deleteSearchPostUC.deleteSearchPost(1L);

        verify(searchPostRepositoryMock).deleteById(1L);
    }

    @Test
    void deleteSearchPostById_shouldNotDeleteSearchPostCauseNonExisting() {
        when(searchPostRepositoryMock.existsById(1L)).thenReturn(false);

        assertThrows(InvalidSearchPostException.class, () -> {
            deleteSearchPostUC.deleteSearchPost(1L);
        });
    }
}