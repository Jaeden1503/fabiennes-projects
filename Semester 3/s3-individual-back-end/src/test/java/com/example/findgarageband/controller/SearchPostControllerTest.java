package com.example.findgarageband.controller;

import com.example.findgarageband.business.ucinterface.*;
import com.example.findgarageband.domain.Comment.Comment;
import com.example.findgarageband.domain.Searchpost.*;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SearchPostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetSearchPostUC getSearchPostUCMock;

    @MockBean
    private GetAllSearchPostsUC getAllSearchPostsUCMock;

    @MockBean
    private CreateSearchPostUC createSearchPostUCMock;

    @MockBean
    private FilterSearchPostsUC filterSearchPostsUCMock;

    @MockBean
    private GetSearchPostByUserUC getSearchPostByUserUCMock;

    @MockBean
    private DeleteSearchPostUC deleteSearchPostUCMock;

    //////////// get all posts and comments ////////////
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void getSearchPostsAndComments_shouldReturn200ResponseWithSearchPostAndCommentsArray() throws Exception {
        LocalDate date = LocalDate.of(2023, 3, 23);

        User user = User.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
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

        GetSearchPostAndCommentsResponse response = GetSearchPostAndCommentsResponse.builder()
                .searchPost(searchPost)
                .comments(List.of(comment1, comment2))
                .build();

        when(getSearchPostUCMock.getSearchPostAndComments(3L)).thenReturn(response);

        mockMvc.perform(get("/searchposts/{id}", 3))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {       
                                "searchPost": {
                                        "id": 3,
                                        "title": "this is searchpost 2",
                                        "description": "second description",
                                        "searchingBand": true,
                                        "date": "2023-03-23",
                                        "instrument": "piano",
                                        "province": "Gelderland",
                                        "user": {
                                            "id": 1,
                                            "username": "fabienne",
                                            "email": "fabienne@gmail.com"
                                        }
                                    },
                                "comments": [
                                     {
                                         "id": 1,
                                         "description": "hello",
                                         "date": "2023-03-23",
                                         "user": {
                                             "id": 1,
                                             "username": "fabienne",
                                             "email": "fabienne@gmail.com"
                                         },
                                         "searchPost": {
                                             "id": 3,
                                             "title": "this is searchpost 2",
                                             "description": "second description",
                                             "searchingBand": true,
                                             "date": "2023-03-23",
                                             "instrument": "piano",
                                             "province": "Gelderland",
                                             "user": {
                                                 "id": 1,
                                                 "username": "fabienne",
                                                 "email": "fabienne@gmail.com"
                                             }
                                         }
                                     },
                                     {
                                         "id": 2,
                                         "description": "bye",
                                         "date": "2023-03-23",
                                         "user": {
                                             "id": 1,
                                             "username": "fabienne",
                                             "email": "fabienne@gmail.com"
                                         },
                                         "searchPost": {
                                             "id": 3,
                                             "title": "this is searchpost 2",
                                             "description": "second description",
                                             "searchingBand": true,
                                             "date": "2023-03-23",
                                             "instrument": "piano",
                                             "province": "Gelderland",
                                             "user": {
                                                 "id": 1,
                                                 "username": "fabienne",
                                                 "email": "fabienne@gmail.com"
                                             }
                                         }
                                     }
                            
                                ]}
                        """));

        verify(getSearchPostUCMock).getSearchPostAndComments(3L);
    }

    //////////// get all posts ////////////
    @Test
    void getSearchPosts_shouldReturn200ResponseWithSearchPostsArray() throws Exception {
        GetAllSearchPostsResponse response = GetAllSearchPostsResponse.builder()
                .searchPosts(getAllSearchPosts())
                .build();

        when(getAllSearchPostsUCMock.getAllSearchPosts()).thenReturn(response);

        mockMvc.perform(get("/searchposts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {       
                                "searchPosts": [
                                    {
                                        "id": 1,
                                        "title": "very cool title",
                                        "description": "this is an interesting description",
                                        "searchingBand": true,
                                        "date": "2023-03-23",
                                        "instrument": "guitar",
                                        "province": "Noord-Brabant",
                                        "user": {
                                            "id": 1,
                                            "username": "fabienne",
                                            "email": "fabienne@gmail.com"
                                        }
                                    },
                                    {
                                        "id": 2,
                                        "title": "second very cool title",
                                        "description": "new description",
                                        "searchingBand": true,
                                        "date": "2023-03-23",
                                        "instrument": "piano",
                                        "province": "Noord-Brabant",
                                        "user": {
                                            "id": 1,
                                            "username": "fabienne",
                                            "email": "fabienne@gmail.com"
                                        }
                                    }
                                ]}
                        """));

        verify(getAllSearchPostsUCMock).getAllSearchPosts();
        verifyNoInteractions(createSearchPostUCMock);
    }

    //////////// get all paginated posts ////////////
    @WithMockUser(roles = "ADMIN")
    @Test
    void getPaginatedSearchPosts_shouldReturn200ResponseWithSearchPostsArray() throws Exception {
        GetPostsPaginatedResponse response = GetPostsPaginatedResponse.builder()
                .searchPosts(getAllSearchPosts())
                .totalPostAmount(getAllSearchPosts().stream().count())
                .build();

        when(getAllSearchPostsUCMock.getAllSearchPostsPaginated(1)).thenReturn(response);

        mockMvc.perform(get("/searchposts/paginated/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {       
                                "searchPosts": [
                                    {
                                        "id": 1,
                                        "title": "very cool title",
                                        "description": "this is an interesting description",
                                        "searchingBand": true,
                                        "date": "2023-03-23",
                                        "instrument": "guitar",
                                        "province": "Noord-Brabant",
                                        "user": {
                                            "id": 1,
                                            "username": "fabienne",
                                            "email": "fabienne@gmail.com"
                                        }
                                    },
                                    {
                                        "id": 2,
                                        "title": "second very cool title",
                                        "description": "new description",
                                        "searchingBand": true,
                                        "date": "2023-03-23",
                                        "instrument": "piano",
                                        "province": "Noord-Brabant",
                                        "user": {
                                            "id": 1,
                                            "username": "fabienne",
                                            "email": "fabienne@gmail.com"
                                        }
                                    }
                                ],
                                    "totalPostAmount": 2
                                }
                        """));

        verify(getAllSearchPostsUCMock).getAllSearchPostsPaginated(1);
        verifyNoInteractions(createSearchPostUCMock);
    }

    //////////// get post ////////////
    @Test
    void getSearchPost_shouldReturn200ResponseWithSearchPost() throws Exception {
        LocalDate date = LocalDate.of(2023, 3, 23);

        User user = User.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .build();

        SearchPost response = SearchPost.builder()
                .id(1L)
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .user(user)
                .build();

        when(getSearchPostUCMock.getSearchPost(1L)).thenReturn(response);

        mockMvc.perform(get("/searchposts/searchpost/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            { 
                                "id": 1,
                                "title": "very cool title",
                                "description": "this is an interesting description",
                                "searchingBand": false,
                                "date": "2023-03-23",
                                "instrument": "guitar",
                                "province": "Noord-Brabant",
                                "user": {
                                    "id": 1,
                                    "username": "fabienne",
                                    "email": "fabienne@gmail.com"
                                }       
                            }
                        """));

        verify(getSearchPostUCMock).getSearchPost(1L);
    }

    //////////// get all posts by user ////////////
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void getAllSearchPostsByUser_shouldReturn200ResponseWithSearchPostsArray() throws Exception {
        GetAllSearchPostsResponse response = GetAllSearchPostsResponse.builder()
                .searchPosts(getAllSearchPosts())
                .build();

        when(getSearchPostByUserUCMock.getAllSearchPostsByUserId(1L)).thenReturn(response);

        mockMvc.perform(get("/searchposts/user/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {       
                                "searchPosts": [
                                    {
                                        "id": 1,
                                        "title": "very cool title",
                                        "description": "this is an interesting description",
                                        "searchingBand": true,
                                        "date": "2023-03-23",
                                        "instrument": "guitar",
                                        "province": "Noord-Brabant",
                                        "user": {
                                            "id": 1,
                                            "username": "fabienne",
                                            "email": "fabienne@gmail.com"
                                        }
                                    },
                                    {
                                        "id": 2,
                                        "title": "second very cool title",
                                        "description": "new description",
                                        "searchingBand": true,
                                        "date": "2023-03-23",
                                        "instrument": "piano",
                                        "province": "Noord-Brabant",
                                        "user": {
                                            "id": 1,
                                            "username": "fabienne",
                                            "email": "fabienne@gmail.com"
                                        }
                                    }
                                ]}
                        """));

        verify(getSearchPostByUserUCMock).getAllSearchPostsByUserId(1L);
    }

    //////////// create post ////////////
    @WithMockUser(roles = "USER")
    @Test
    void createComment_shouldCreateAndReturn201_WhenRequestValid() throws Exception {
        LocalDate date = LocalDate.of(2023, 3, 23);

        CreateSearchPostRequest request = CreateSearchPostRequest.builder()
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .userId(1L)
                .build();

        when(createSearchPostUCMock.createSearchPost(request)).thenReturn(CreateSearchPostResponse.builder().searchPostId(100L).build());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/searchposts")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""                            
                                {                              
                                    "title": "very cool title",
                                    "description": "this is an interesting description",
                                    "searchingBand": "false",
                                    "date": "2023-03-23",
                                    "instrument": "guitar",
                                    "province": "Noord-Brabant",
                                    "userId": 1
                                }
                                 """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                       {"searchPostId": 100}
                    """));

        verify(createSearchPostUCMock).createSearchPost(request);
    }

    //////////// filter posts ////////////
    @Test
    void getFilteredSearchPosts_shouldReturn200ResponseWithSearchPostsArray() throws Exception {
        FilterSearchPostRequest request = FilterSearchPostRequest.builder()
                .searchingBand(true)
                .instrument("")
                .province("Noord-Brabant")
                .build();

        FilterSearchPostResponse response = FilterSearchPostResponse.builder()
                .searchPosts(getAllSearchPosts())
                .build();

        when(filterSearchPostsUCMock.filterSearchPosts(request)).thenReturn(response);

        mockMvc.perform(get("/searchposts/filter")
                        .param("searchingBand", "true")
                        .param("instrument", "")
                        .param("province", "Noord-Brabant"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {       
                                "searchPosts": [
                                    {
                                        "id": 1,
                                        "title": "very cool title",
                                        "description": "this is an interesting description",
                                        "searchingBand": true,
                                        "date": "2023-03-23",
                                        "instrument": "guitar",
                                        "province": "Noord-Brabant",
                                        "user": {
                                            "id": 1,
                                            "username": "fabienne",
                                            "email": "fabienne@gmail.com"
                                        }
                                    },
                                    {
                                        "id": 2,
                                        "title": "second very cool title",
                                        "description": "new description",
                                        "searchingBand": true,
                                        "date": "2023-03-23",
                                        "instrument": "piano",
                                        "province": "Noord-Brabant",
                                        "user": {
                                            "id": 1,
                                            "username": "fabienne",
                                            "email": "fabienne@gmail.com"
                                        }
                                    }
                                ]}
                        """));

        verify(filterSearchPostsUCMock).filterSearchPosts(request);
    }

    //////////// delete post ////////////
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void deleteSearchPost_shouldDeleteAndReturn204_WhenRequestValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/searchposts/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteSearchPostUCMock).deleteSearchPost(1L);
    }


    private List<SearchPost> getAllSearchPosts() {
        LocalDate date = LocalDate.of(2023, 3, 23);

        User user = User.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .build();

        SearchPost searchPost = SearchPost.builder()
                .id(1L)
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(true)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .user(user)
                .build();

        SearchPost searchPost2 = SearchPost.builder()
                .id(2L)
                .title("second very cool title")
                .description("new description")
                .searchingBand(true)
                .date(date)
                .instrument("piano")
                .province("Noord-Brabant")
                .user(user)
                .build();

        List<SearchPost> searchPostList = new ArrayList<>();
        searchPostList.add(searchPost);
        searchPostList.add(searchPost2);

        return searchPostList;
    }
}