package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.ucinterface.CreateSearchPostUC;
import com.example.findgarageband.business.exception.UnauthorizedDataAccessException;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.domain.Searchpost.CreateSearchPostRequest;
import com.example.findgarageband.domain.Searchpost.CreateSearchPostResponse;
import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.RoleEnum;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import com.example.findgarageband.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CreateSearchPostUCImpl implements CreateSearchPostUC {
    private final SearchPostRepository searchPostRepository;
    private final UserRepository userRepository;
    private AccessToken requestAccessToken;

    @Transactional
    @Override
    public CreateSearchPostResponse createSearchPost(CreateSearchPostRequest request) {
        if (!requestAccessToken.hasRole(RoleEnum.USER.name())) {
            throw new UnauthorizedDataAccessException("LOGGING IN IS REQUIRED TO CREATE A POST");
        }

        UserEntity user = userRepository.findUserEntityById(request.getUserId());

        SearchPostEntity newSearchPost = SearchPostEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .searchingBand(request.getSearchingBand())
                .date(request.getDate())
                .instrument(request.getInstrument())
                .province(request.getProvince())
                .userEntity(user)
                .build();

        SearchPostEntity savedSearchPost = searchPostRepository.save(newSearchPost);

        return CreateSearchPostResponse.builder().searchPostId(savedSearchPost.getId()).build();
    }
}
