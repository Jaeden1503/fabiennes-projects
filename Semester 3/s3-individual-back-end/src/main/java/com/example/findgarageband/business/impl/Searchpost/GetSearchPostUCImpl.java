package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.business.ucinterface.GetSearchPostUC;
import com.example.findgarageband.business.exception.InvalidSearchPostException;
import com.example.findgarageband.business.impl.CommentConverter;
import com.example.findgarageband.business.impl.SearchPostConverter;
import com.example.findgarageband.business.impl.UserConverter;
import com.example.findgarageband.domain.Comment.Comment;
import com.example.findgarageband.domain.Searchpost.GetSearchPostAndCommentsResponse;
import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.persistence.CommentRepository;
import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GetSearchPostUCImpl implements GetSearchPostUC {
    private final SearchPostRepository searchPostRepository;
    private final CommentRepository commentRepository;
    private final AccessTokenValidator accessTokenValidator;

    @Transactional
    @Override
    public GetSearchPostAndCommentsResponse getSearchPostAndComments(Long searchPostId) {
        if (!searchPostRepository.existsById(searchPostId)) {
            throw new InvalidSearchPostException();
        }

        SearchPostEntity searchPost = searchPostRepository.findSearchPostEntityById(searchPostId);

        accessTokenValidator.checkAccessToken(searchPost.getUserEntity().getId());

        List<Comment> comments = commentRepository.findCommentsBySearchPostId(searchPostId)
                .stream()
                .map(CommentConverter::convert)
                .toList();

        return GetSearchPostAndCommentsResponse.builder()
                .searchPost(SearchPostConverter.convert(searchPost))
                .comments(comments)
                .build();
    }

    @Transactional
    @Override
    public SearchPost getSearchPost(Long searchPostId) {
        if (!searchPostRepository.existsById(searchPostId)) {
            throw new InvalidSearchPostException();
        }

        SearchPostEntity searchPost = searchPostRepository.findSearchPostEntityById(searchPostId);

        return SearchPost.builder()
                .id(searchPost.getId())
                .title(searchPost.getTitle())
                .description(searchPost.getDescription())
                .searchingBand(searchPost.getSearchingBand())
                .date(searchPost.getDate())
                .instrument(searchPost.getInstrument())
                .province(searchPost.getProvince())
                .user(UserConverter.convert(searchPost.getUserEntity()))
                .build();
    }
}
