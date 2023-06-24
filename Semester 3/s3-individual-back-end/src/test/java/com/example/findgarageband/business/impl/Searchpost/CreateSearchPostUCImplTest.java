package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.exception.UnauthorizedDataAccessException;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.domain.Searchpost.CreateSearchPostRequest;
import com.example.findgarageband.domain.Searchpost.CreateSearchPostResponse;
import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.UserRepository;
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
class CreateSearchPostUCImplTest {
    @Mock
    private SearchPostRepository searchPostRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private AccessToken accessToken;
    @InjectMocks
    private CreateSearchPostUCImpl createSearchPostUC;

    @Test
    void createSearchPost_shouldSaveSearchPostConverted() {
        //create mock entity that gets 'saved' in the database
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

        //create mock entity that gets returned from saving
        SearchPostEntity returnedSearchPost = SearchPostEntity.builder()
                .id(1L)
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .userEntity(userEntity)
                .build();

        //tell mockito what to do when an entity gets saved
        when(accessToken.hasRole(RoleEnum.USER.name())).thenReturn(true);
        when(userRepositoryMock.findUserEntityById(1L)).thenReturn(userEntity);
        when(searchPostRepositoryMock.save(searchPostEntity)).thenReturn(returnedSearchPost);

        //create searchpost request
        CreateSearchPostRequest request = CreateSearchPostRequest.builder()
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .userId(1L)
                .build();

        //execute create function from implementation class
        CreateSearchPostResponse actualResponse = createSearchPostUC.createSearchPost(request);

        //create mock expected response
        CreateSearchPostResponse expectedResponse = CreateSearchPostResponse.builder()
                .searchPostId(1L)
                .build();

        //assertEqual expected vs actual
        assertEquals(expectedResponse, actualResponse);

        //verify that the mock repo only executed once
        verify(searchPostRepositoryMock).save(searchPostEntity);
        verify(userRepositoryMock).findUserEntityById(1L);
    }

    @Test
    void createSearchPost_shouldNotSaveSearchPostConverted() {
        LocalDate date = LocalDate.of(2020, 1, 8);

        CreateSearchPostRequest request = CreateSearchPostRequest.builder()
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .userId(1L)
                .build();

        when(accessToken.hasRole(RoleEnum.USER.name())).thenReturn(false);

        assertThrows(UnauthorizedDataAccessException.class, () -> {
            createSearchPostUC.createSearchPost(request);
        });
    }
}
