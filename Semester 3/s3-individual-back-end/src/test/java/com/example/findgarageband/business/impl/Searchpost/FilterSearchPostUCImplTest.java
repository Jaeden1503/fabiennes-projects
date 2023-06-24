package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.domain.Searchpost.FilterSearchPostRequest;
import com.example.findgarageband.domain.Searchpost.FilterSearchPostResponse;
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
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterSearchPostUCImplTest {
    @Mock
    private SearchPostRepository searchPostRepositoryMock;
    @InjectMocks
    private FilterSearchPostsUCImpl filterSearchPostsUC;

    @Test
    void filterSearchPosts_shouldReturnFilteredSearchPostsConverted() {
        //make filter request entity
        FilterSearchPostRequest request = FilterSearchPostRequest.builder()
                .searchingBand(true)
                .instrument("piano")
                .province("Noord-brabant")
                .build();

        SearchPostEntity searchPostEntity = SearchPostEntity.builder()
                .searchingBand(true)
                .instrument("piano")
                .province("Noord-brabant")
                .build();

        //make returned items
        List<SearchPostEntity> dummyList = dummySearchPostList();

        //when
        when(searchPostRepositoryMock.findAll(Example.of(searchPostEntity))).thenReturn(dummyList);

        //actual function execution
        FilterSearchPostResponse actualResult = filterSearchPostsUC.filterSearchPosts(request);

        //expected result
        User user = User.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .build();

        LocalDate date = LocalDate.of(2020, 1, 8);
        SearchPost searchPost = SearchPost.builder()
                .id(1L)
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(true)
                .date(date)
                .instrument("piano")
                .province("Noord-Brabant")
                .user(user)
                .build();

        FilterSearchPostResponse expectedResult = FilterSearchPostResponse.builder()
                .searchPosts(List.of(searchPost))
                .build();

        //assert equals
        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult.getSearchPosts().size(), actualResult.getSearchPosts().size());

        //verify
        verify(searchPostRepositoryMock).findAll(Example.of(searchPostEntity));
    }

    @Test
    void filterSearchPosts_notAllParamsFilledButShouldReturnSearchPosts() {
        FilterSearchPostRequest request = FilterSearchPostRequest.builder()
                .searchingBand(true)
                .instrument("")
                .province("")
                .build();

        SearchPostEntity searchPostEntity = SearchPostEntity.builder()
                .searchingBand(true)
                .build();

        List<SearchPostEntity> dummyList = dummySearchPostList();

        when(searchPostRepositoryMock.findAll(Example.of(searchPostEntity))).thenReturn(dummyList);

        FilterSearchPostResponse actualResult = filterSearchPostsUC.filterSearchPosts(request);

        //expected result
        User user = User.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .build();

        LocalDate date = LocalDate.of(2020, 1, 8);
        SearchPost searchPost = SearchPost.builder()
                .id(1L)
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(true)
                .date(date)
                .instrument("piano")
                .province("Noord-Brabant")
                .user(user)
                .build();

        FilterSearchPostResponse expectedResult = FilterSearchPostResponse.builder()
                .searchPosts(List.of(searchPost))
                .build();

        //assert equals
        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult.getSearchPosts().size(), actualResult.getSearchPosts().size());

        //verify
        verify(searchPostRepositoryMock).findAll(Example.of(searchPostEntity));
    }


    private List<SearchPostEntity> dummySearchPostList() {
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
                .searchingBand(true)
                .date(date)
                .instrument("piano")
                .province("Noord-Brabant")
                .userEntity(userEntity)
                .build());

//        searchPostList.add(SearchPostEntity.builder()
//                .id(2L)
//                .title("very cool second title")
//                .description("this is an interesting second description")
//                .searchingBand(true)
//                .date(date)
//                .instrument("guitar")
//                .province("Utrecht")
//                .userEntity(userEntity)
//                .build());

        return searchPostList;
    }
}
