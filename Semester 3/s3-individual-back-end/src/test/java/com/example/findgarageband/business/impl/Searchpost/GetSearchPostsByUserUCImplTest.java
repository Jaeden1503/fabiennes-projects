package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.domain.Searchpost.GetAllSearchPostsResponse;
import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.domain.User.User;
import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.entity.RoleEnum;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import com.example.findgarageband.persistence.entity.UserEntity;
import com.example.findgarageband.persistence.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetSearchPostsByUserUCImplTest {
    @Mock
    private SearchPostRepository searchPostRepositoryMock;
    @Mock
    private AccessTokenValidator accessTokenValidator;
    @InjectMocks
    private GetSearchPostsByUserUCImpl getSearchPostsByUserUC;

    @Test
    void getAllSearchPostsByUser_shouldReturnAllSearchPostsConverted() {
        LocalDate date = LocalDate.of(2020, 1, 8);

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
                .build();

        userEntity.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .id(1L)
                        .user(userEntity)
                        .role(RoleEnum.ADMIN)
                        .build()
        ));

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

        when(searchPostRepositoryMock.findSearchPostEntitiesByUserId(1L)).thenReturn(searchPostList);

        GetAllSearchPostsResponse actualResult = getSearchPostsByUserUC.getAllSearchPostsByUserId(1L);

        User user = User.builder()
                .id(1L)
                .username("fabienne")
                .email("fabienne@gmail.com")
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
        verify(searchPostRepositoryMock).findSearchPostEntitiesByUserId(1L);
    }
}