package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.impl.SearchPostConverter;
import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.domain.User.User;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import com.example.findgarageband.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SearchPostEntityConverterTest {

    @Test
    void shouldConvertAllSearchPostFieldsToDomain() {
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

        SearchPost actualSearchPost = SearchPostConverter.convert(searchPostEntity);

        User user = User.builder()
                .id(1L)
                .username("cool_user")
                .email("test@gmail.com")
                .build();

        SearchPost expectedSearchPost = SearchPost.builder()
                .id(1L)
                .title("very cool title")
                .description("this is an interesting description")
                .searchingBand(false)
                .date(date)
                .instrument("guitar")
                .province("Noord-Brabant")
                .user(user)
                .build();

        assertEquals(expectedSearchPost, actualSearchPost);
    }

}
