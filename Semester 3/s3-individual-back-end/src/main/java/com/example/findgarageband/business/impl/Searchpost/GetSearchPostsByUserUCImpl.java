package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.business.ucinterface.GetSearchPostByUserUC;
import com.example.findgarageband.business.impl.SearchPostConverter;
import com.example.findgarageband.domain.Searchpost.GetAllSearchPostsResponse;
import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.persistence.SearchPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GetSearchPostsByUserUCImpl implements GetSearchPostByUserUC {
    private final SearchPostRepository searchPostRepository;
    private final AccessTokenValidator accessTokenValidator;

    @Transactional
    @Override
    public GetAllSearchPostsResponse getAllSearchPostsByUserId(Long userId) {
        accessTokenValidator.checkAccessToken(userId);

        List<SearchPost> searchPosts = searchPostRepository.findSearchPostEntitiesByUserId(userId)
                .stream()
                .map(SearchPostConverter::convert)
                .toList();

        return GetAllSearchPostsResponse.builder()
                .searchPosts(searchPosts)
                .build();
    }

}
