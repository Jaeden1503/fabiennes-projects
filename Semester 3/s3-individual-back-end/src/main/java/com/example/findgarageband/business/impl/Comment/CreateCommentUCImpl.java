package com.example.findgarageband.business.impl.Comment;

import com.example.findgarageband.business.ucinterface.CreateCommentUC;
import com.example.findgarageband.business.exception.UnauthorizedDataAccessException;
import com.example.findgarageband.domain.AccessToken;
import com.example.findgarageband.domain.Comment.CreateCommentRequest;
import com.example.findgarageband.domain.Comment.CreateCommentResponse;
import com.example.findgarageband.persistence.CommentRepository;
import com.example.findgarageband.persistence.SearchPostRepository;
import com.example.findgarageband.persistence.UserRepository;
import com.example.findgarageband.persistence.entity.CommentEntity;
import com.example.findgarageband.persistence.entity.RoleEnum;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import com.example.findgarageband.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CreateCommentUCImpl implements CreateCommentUC {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final SearchPostRepository searchPostRepository;
    private AccessToken requestAccessToken;

    @Transactional
    @Override
    public CreateCommentResponse createComment(CreateCommentRequest request) {
        if (!requestAccessToken.hasRole(RoleEnum.USER.name())) {
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }

        UserEntity user = userRepository.findUserEntityById(request.getUserId());
        SearchPostEntity searchPost = searchPostRepository.findSearchPostEntityById(request.getSearchPostId());

        CommentEntity newComment = CommentEntity.builder()
                .description(request.getDescription())
                .date(request.getDate())
                .userEntity(user)
                .searchPostEntity(searchPost)
                .build();

        CommentEntity savedComment = commentRepository.save(newComment);

        return CreateCommentResponse.builder().commentId(savedComment.getId()).build();
    }
}
