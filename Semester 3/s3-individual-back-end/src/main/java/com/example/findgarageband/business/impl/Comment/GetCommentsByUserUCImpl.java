package com.example.findgarageband.business.impl.Comment;

import com.example.findgarageband.business.ucinterface.AccessTokenValidator;
import com.example.findgarageband.business.ucinterface.GetCommentsByUserUC;
import com.example.findgarageband.business.impl.CommentConverter;
import com.example.findgarageband.domain.Comment.Comment;
import com.example.findgarageband.domain.Comment.GetAllCommentsResponse;
import com.example.findgarageband.persistence.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GetCommentsByUserUCImpl implements GetCommentsByUserUC {
    private final CommentRepository commentRepository;
    private final AccessTokenValidator accessTokenValidator;

    @Transactional
    @Override
    public GetAllCommentsResponse getCommentsByUserId(Long userId) {
        accessTokenValidator.checkAccessToken(userId);

        List<Comment> comments = commentRepository.findCommentsByUserId(userId)
                .stream()
                .map(CommentConverter::convert)
                .toList();

        return GetAllCommentsResponse.builder()
                .comments(comments)
                .build();
    }
}
