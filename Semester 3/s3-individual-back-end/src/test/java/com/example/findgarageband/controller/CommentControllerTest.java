package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.CreateCommentUC;
import com.example.findgarageband.business.ucinterface.DeleteCommentUC;
import com.example.findgarageband.business.ucinterface.GetAllCommentsUC;
import com.example.findgarageband.business.ucinterface.GetCommentsByUserUC;
import com.example.findgarageband.domain.Comment.Comment;
import com.example.findgarageband.domain.Comment.CreateCommentRequest;
import com.example.findgarageband.domain.Comment.CreateCommentResponse;
import com.example.findgarageband.domain.Comment.GetAllCommentsResponse;
import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.domain.User.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllCommentsUC commentsUCMock;

    @MockBean
    private GetCommentsByUserUC getCommentsByUserUCMock;

    @MockBean
    private CreateCommentUC createCommentUCMock;

    @MockBean
    private DeleteCommentUC deleteCommentUC;

    //////////// get all comments ////////////
    @WithMockUser(roles = {"ADMIN"})
    @Test
    void getComments_shouldReturn200ResponseWithCommentsArray() throws Exception {
        GetAllCommentsResponse response = getAll();

        when(commentsUCMock.getAllComments()).thenReturn(response);

        mockMvc.perform(get("/comments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {   
                                "comments": [
                                     {
                                         "id": 1,
                                         "description": "hello",
                                         "date": "2020-01-08",
                                         "user": {
                                             "id": 1,
                                             "username": "cool_user",
                                             "email": "test@gmail.com"
                                         },
                                         "searchPost": {
                                             "id": 3,
                                             "title": "this is searchpost 2",
                                             "description": "second description",
                                             "searchingBand": true,
                                             "date": "2020-01-08",
                                             "instrument": "piano",
                                             "province": "Gelderland",
                                             "user": {
                                                 "id": 1,
                                                 "username": "cool_user",
                                                 "email": "test@gmail.com"
                                             }
                                         }
                                     },
                                     {
                                         "id": 2,
                                         "description": "bye",
                                         "date": "2020-01-08",
                                         "user": {
                                             "id": 1,
                                             "username": "cool_user",
                                             "email": "test@gmail.com"
                                         },
                                         "searchPost": {
                                             "id": 3,
                                             "title": "this is searchpost 2",
                                             "description": "second description",
                                             "searchingBand": true,
                                             "date": "2020-01-08",
                                             "instrument": "piano",
                                             "province": "Gelderland",
                                             "user": {
                                                 "id": 1,
                                                 "username": "cool_user",
                                                 "email": "test@gmail.com"
                                             }
                                         }
                                     }
                            
                                ]}
                        """));

        verify(commentsUCMock).getAllComments();
        verifyNoInteractions(createCommentUCMock);
    }

    //////////// get all comments by user ////////////
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void getCommentsByUser_shouldReturn200ResponseWithCommentsArray() throws Exception {
        GetAllCommentsResponse response = getAll();

        when(getCommentsByUserUCMock.getCommentsByUserId(1L)).thenReturn(response);

        mockMvc.perform(get("/comments/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {   
                                "comments": [
                                     {
                                         "id": 1,
                                         "description": "hello",
                                         "date": "2020-01-08",
                                         "user": {
                                             "id": 1,
                                             "username": "cool_user",
                                             "email": "test@gmail.com"
                                         },
                                         "searchPost": {
                                             "id": 3,
                                             "title": "this is searchpost 2",
                                             "description": "second description",
                                             "searchingBand": true,
                                             "date": "2020-01-08",
                                             "instrument": "piano",
                                             "province": "Gelderland",
                                             "user": {
                                                 "id": 1,
                                                 "username": "cool_user",
                                                 "email": "test@gmail.com"
                                             }
                                         }
                                     },
                                     {
                                         "id": 2,
                                         "description": "bye",
                                         "date": "2020-01-08",
                                         "user": {
                                             "id": 1,
                                             "username": "cool_user",
                                             "email": "test@gmail.com"
                                         },
                                         "searchPost": {
                                             "id": 3,
                                             "title": "this is searchpost 2",
                                             "description": "second description",
                                             "searchingBand": true,
                                             "date": "2020-01-08",
                                             "instrument": "piano",
                                             "province": "Gelderland",
                                             "user": {
                                                 "id": 1,
                                                 "username": "cool_user",
                                                 "email": "test@gmail.com"
                                             }
                                         }
                                     }
                            
                                ]}
                        """));

        verify(getCommentsByUserUCMock).getCommentsByUserId(1L);
        verifyNoInteractions(createCommentUCMock);
    }

    //////////// create comment ////////////
    @WithMockUser(roles = "USER")
    @Test
    void createComment_shouldCreateAndReturn201_WhenRequestValid() throws Exception {
        LocalDate date = LocalDate.of(2020, 1, 8);

        CreateCommentRequest request = CreateCommentRequest.builder()
                .description("Yes I would like to join your band")
                .date(date)
                .userId(1L)
                .searchPostId(5L)
                .build();
        when(createCommentUCMock.createComment(request)).thenReturn(CreateCommentResponse.builder().commentId(100L).build());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/comments")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""                            
                                {                              
                                    "description": "Yes I would like to join your band",
                                    "date": "2020-01-08",
                                    "userId": 1,
                                    "searchPostId": 5
                                }
                                 """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                       {"commentId": 100}
                    """));

        verify(createCommentUCMock).createComment(request);
    }

    //////////// delete comment ////////////
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void deleteComment_shouldDeleteAndReturn204_WhenRequestValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/comments/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteCommentUC).deleteComment(1L);
    }


    private GetAllCommentsResponse getAll() {
        LocalDate date = LocalDate.of(2020, 1, 8);

        User user = User.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .build();

        SearchPost searchPost = SearchPost.builder()
                .id(3L)
                .title("this is searchpost 2")
                .description("second description")
                .searchingBand(true)
                .date(date)
                .instrument("piano")
                .province("Gelderland")
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

        return GetAllCommentsResponse.builder()
                .comments(List.of(comment1, comment2))
                .build();
    }
}