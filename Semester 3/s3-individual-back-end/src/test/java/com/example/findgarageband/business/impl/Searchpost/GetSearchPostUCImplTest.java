package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.business.exception.InvalidSearchPostException;
import com.example.findgarageband.domain.Comment.Comment;
import com.example.findgarageband.domain.Searchpost.GetSearchPostAndCommentsResponse;
import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.domain.User.User;
import com.example.findgarageband.persistence.CommentRepository;
import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.entity.CommentEntity;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import com.example.findgarageband.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetSearchPostUCImplTest {
    @Mock
    private SearchPostRepository searchPostRepositoryMock;
    @Mock
    private CommentRepository commentRepositoryMock;
    @Mock
    private AccessTokenValidator accessTokenValidator;
    @InjectMocks
    private GetSearchPostUCImpl getSearchPostUC;

    @Test
    void getSearchPostAndComments_shouldReturnSearchPostByIdConverted() {
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
                .description("description")
                .date(date)
                .userEntity(userEntity)
                .searchPostEntity(searchPostEntity)
                .build();

        when(searchPostRepositoryMock.existsById(searchPostEntity.getId())).thenReturn(true);
        when(commentRepositoryMock.findCommentsBySearchPostId(searchPostEntity.getId())).thenReturn((List.of(commentEntity)));
        when(searchPostRepositoryMock.findSearchPostEntityById(searchPostEntity.getId())).thenReturn(searchPostEntity);

        GetSearchPostAndCommentsResponse actualSearchPost = getSearchPostUC.getSearchPostAndComments(1L);

        User user = User.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .build();

        SearchPost searchPost = SearchPost.builder()
                .id(1L)
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .user(user)
                .build();

        Comment comment = Comment.builder()
                .id(1L)
                .description("description")
                .date(date)
                .user(user)
                .searchPost(searchPost)
                .build();

        GetSearchPostAndCommentsResponse expectedSearchPost = GetSearchPostAndCommentsResponse.builder()
                .searchPost(searchPost)
                .comments(List.of(comment))
                .build();

        assertEquals(expectedSearchPost, actualSearchPost);
        verify(searchPostRepositoryMock).existsById(1L);
        verify(searchPostRepositoryMock).findSearchPostEntityById(1L);
    }

    @Test
    void getSearchPostAndComments_shouldReturnInvalidSearchPostIdException() {
        when(searchPostRepositoryMock.existsById(2L)).thenReturn(false);

        assertThrows(InvalidSearchPostException.class, () -> {
            getSearchPostUC.getSearchPostAndComments(2L);
        });
    }

///////////////////////////

    @Test
    void getSearchPost_shouldReturnSearchPostByIdConverted() {
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

        when(searchPostRepositoryMock.existsById(searchPostEntity.getId())).thenReturn(true);
        when(searchPostRepositoryMock.findSearchPostEntityById(searchPostEntity.getId())).thenReturn(searchPostEntity);

        SearchPost actualSearchPost = getSearchPostUC.getSearchPost(1L);

        User user = User.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .build();

        SearchPost searchPost = SearchPost.builder()
                .id(1L)
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .user(user)
                .build();

        assertEquals(searchPost, actualSearchPost);
        verify(searchPostRepositoryMock).existsById(1L);
        verify(searchPostRepositoryMock).findSearchPostEntityById(1L);
    }

    @Test
    void getSearchPost_shouldReturnInvalidSearchPostIdException() {
        when(searchPostRepositoryMock.existsById(2L)).thenReturn(false);

        assertThrows(InvalidSearchPostException.class, () -> {
            getSearchPostUC.getSearchPost(2L);
        });
    }
}