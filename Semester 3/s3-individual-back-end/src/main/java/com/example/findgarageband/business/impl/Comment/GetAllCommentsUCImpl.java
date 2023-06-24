package com.example.findgarageband.business.impl.Comment;

import com.example.findgarageband.business.ucinterface.GetAllCommentsUC;
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
public class GetAllCommentsUCImpl implements GetAllCommentsUC {
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public GetAllCommentsResponse getAllComments() {
        List<Comment> comments = commentRepository.findAll().stream()
                .map(CommentConverter::convert)
                .toList();

        return GetAllCommentsResponse.builder()
                .comments(comments)
                .build();
    }
}
