package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.domain.Searchpost.GetAllSearchPostsResponse;
import com.example.findgarageband.domain.Searchpost.GetPostsPaginatedResponse;
import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.domain.User.User;
import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import com.example.findgarageband.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllSearchPostsUCImplTest {
    @Mock
    private SearchPostRepository searchPostRepositoryMock;
    @InjectMocks
    private GetAllSearchPostsUCImpl getAllSearchPostsUC;

    @Test
    void getAllSearchPosts_shouldReturnAllSearchPostsConverted() {
        LocalDate date = LocalDate.of(2020, 1, 8);

        List<SearchPostEntity> searchPostList = getSPEntityResponse();

        when(searchPostRepositoryMock.findAll()).thenReturn(searchPostList);

        GetAllSearchPostsResponse actualResult = getAllSearchPostsUC.getAllSearchPosts();

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

        GetAllSearchPostsResponse expectedResult = GetAllSearchPostsResponse.builder()
                .searchPosts(List.of(searchPost, searchPost2))
                .build();

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult.getSearchPosts().size(), actualResult.getSearchPosts().size());
        verify(searchPostRepositoryMock).findAll();
    }

    @Test
    void getAllPaginatedPosts_shouldReturnPosts() {
        LocalDate date = LocalDate.of(2020, 1, 8);

        List<SearchPostEntity> searchPostList = getSPEntityResponse();

        when(searchPostRepositoryMock.findAll(Pageable.ofSize(5).withPage(0))).thenReturn((new PageImpl<>(searchPostList)));
        when(searchPostRepositoryMock.count()).thenReturn(searchPostList.stream().count());
        GetPostsPaginatedResponse actualResult = getAllSearchPostsUC.getAllSearchPostsPaginated(1);

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

        GetPostsPaginatedResponse expectedResult = GetPostsPaginatedResponse.builder()
                .searchPosts(List.of(searchPost, searchPost2))
                .totalPostAmount(2L)
                .build();

        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult.getSearchPosts().size(), actualResult.getSearchPosts().size());
        verify(searchPostRepositoryMock).findAll(Pageable.ofSize(5).withPage(0));
    }

    private List<SearchPostEntity> getSPEntityResponse() {
        LocalDate date = LocalDate.of(2020, 1, 8);

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .build();

        List<SearchPostEntity> searchPostList = new ArrayList<>();

        searchPostList.add(SearchPostEntity.builder()
                .id(1L)
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .userEntity(userEntity)
                .build());

        searchPostList.add(SearchPostEntity.builder()
                .id(2L)
                .title("second very cool title")
                .description("new description")
                .searchingBand(true)
                .date(date)
                .instrument("piano")
                .province("Noord-Brabant")
                .userEntity(userEntity)
                .build());

        return searchPostList;
    }
}