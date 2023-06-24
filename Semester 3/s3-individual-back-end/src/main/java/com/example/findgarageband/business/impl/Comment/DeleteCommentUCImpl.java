package com.example.findgarageband.business.impl.Comment;

import com.example.findgarageband.business.exception.InvalidIdException;
import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.business.ucinterface.DeleteCommentUC;
import com.example.findgarageband.persistence.CommentRepository;
import com.example.findgarageband.persistence.entity.CommentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class DeleteCommentUCImpl implements DeleteCommentUC {
    private final CommentRepository commentRepository;
    private final AccessTokenValidator accessTokenValidator;

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new InvalidIdException();
        }

        CommentEntity commentEntity = commentRepository.findCommentEntityById(commentId);
        accessTokenValidator.checkAccessToken(commentEntity.getUserEntity().getId());

        commentRepository.deleteById(commentId);
    }
}
