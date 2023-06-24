package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.business.ucinterface.DeleteSearchPostUC;
import com.example.findgarageband.business.exception.InvalidSearchPostException;
import com.example.findgarageband.persistence.CommentRepository;
import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class DeleteSearchPostUCImpl implements DeleteSearchPostUC {
    private final SearchPostRepository searchPostRepository;
    private final CommentRepository commentRepository;
    private final AccessTokenValidator accessTokenValidator;

    @Transactional
    @Override
    public void deleteSearchPost(Long searchPostId) {
        if (!searchPostRepository.existsById(searchPostId)) {
            throw new InvalidSearchPostException();
        }

        SearchPostEntity searchPostEntity = searchPostRepository.findSearchPostEntityById(searchPostId);
        accessTokenValidator.checkAccessToken(searchPostEntity.getUserEntity().getId());

        commentRepository.deleteCommentsBySearchPostId(searchPostId);
        searchPostRepository.deleteById(searchPostId);
    }
}
