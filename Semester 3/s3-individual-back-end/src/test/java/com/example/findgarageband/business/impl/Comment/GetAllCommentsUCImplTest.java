package com.example.findgarageband.business.impl.Comment;

import com.example.findgarageband.domain.Comment.Comment;
import com.example.findgarageband.domain.Comment.GetAllCommentsResponse;
import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.domain.User.User;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllCommentsUCImplTest {
    @Mock
    private CommentRepository commentRepositoryMock;
    @InjectMocks
    private GetAllCommentsUCImpl getAllCommentsUC;

    @Test
    void getAllComments_shouldReturnAllComments() {
        LocalDate date = LocalDate.of(2020, 1, 8);

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

        List<CommentEntity> commentList = new ArrayList<>();
        commentList.add(CommentEntity.builder()
                .id(1L)
                .description("hello")
                .date(date)
                .userEntity(userEntity)
                .searchPostEntity(searchPostEntity)
                .build());

        commentList.add(CommentEntity.builder()
                .id(2L)
                .description("bye")
                .date(date)
                .userEntity(userEntity)
                .searchPostEntity(searchPostEntity)
                .build());

        when(commentRepositoryMock.findAll()).thenReturn(commentList);

        GetAllCommentsResponse actualResponse = getAllCommentsUC.getAllComments();

        User user = User.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .build();

        SearchPost searchPost = SearchPost.builder()
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .user(user)
                .build();

        Comment comment1 = Comment.builder()
                .id(1L)
                .description("hello")
                .date(date)
                .user(user)
                .searchPost(searchPost)
                .build();

        Comment comment2 = Comment.builder()
                .id(2L)
                .description("bye")
                .date(date)
                .user(user)
                .searchPost(searchPost)
                .build();

        GetAllCommentsResponse expectedResponse = GetAllCommentsResponse.builder()
                .comments(List.of(comment1, comment2))
                .build();

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedResponse.getComments().size(), actualResponse.getComments().size());
        verify(commentRepositoryMock).findAll();
    }

}