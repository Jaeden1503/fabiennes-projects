package com.example.findgarageband.business.impl.Comment;

import com.example.findgarageband.business.exception.UnauthorizedDataAccessException;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.domain.Comment.CreateCommentRequest;
import com.example.findgarageband.domain.Comment.CreateCommentResponse;
import com.example.findgarageband.persistence.CommentRepository;
import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.CommentEntity;
import com.example.findgarageband.persistence.entity.RoleEnum;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import com.example.findgarageband.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCommentUCImplTest {
    @Mock
    private CommentRepository commentRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private SearchPostRepository searchPostRepositoryMock;
    @Mock
    private AccessToken accessToken;
    @InjectMocks
    private CreateCommentUCImpl createCommentUC;

    @Test
    void createComment_shouldSaveComment() {
        LocalDate date = LocalDate.of(2020, 1, 8);

        CreateCommentRequest request = CreateCommentRequest.builder()
                .description("hello")
                .date(date)
                .userId(1L)
                .searchPostId(1L)
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .build();

        SearchPostEntity searchPostEntity = SearchPostEntity.builder()
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .userEntity(userEntity)
                .build();

        CommentEntity newComment = CommentEntity.builder()
                .description("hello")
                .date(date)
                .userEntity(userEntity)
                .searchPostEntity(searchPostEntity)
                .build();

        CommentEntity returnedComment = CommentEntity.builder()
                .id(1L)
                .description("hello")
                .date(date)
                .userEntity(userEntity)
                .searchPostEntity(searchPostEntity)
                .build();

        when(accessToken.hasRole(RoleEnum.USER.name())).thenReturn(true);
        when(userRepositoryMock.findUserEntityById(request.getUserId())).thenReturn(userEntity);
        when(searchPostRepositoryMock.findSearchPostEntityById(request.getSearchPostId())).thenReturn(searchPostEntity);
        when(commentRepositoryMock.save(newComment)).thenReturn(returnedComment);

        CreateCommentResponse actualResponse = createCommentUC.createComment(request);

        CreateCommentResponse expectedResponse = CreateCommentResponse.builder()
                .commentId(1L)
                .build();

        assertEquals(expectedResponse, actualResponse);

        verify(commentRepositoryMock).save(newComment);
    }

    @Test
    void createComment_shouldNotSaveComment() {
        LocalDate date = LocalDate.of(2020, 1, 8);

        CreateCommentRequest request = CreateCommentRequest.builder()
                .description("hello")
                .date(date)
                .userId(1L)
                .searchPostId(1L)
                .build();

        when(accessToken.hasRole(RoleEnum.USER.name())).thenReturn(false);

        assertThrows(UnauthorizedDataAccessException.class, () -> {
            createCommentUC.createComment(request);
        });
    }
}