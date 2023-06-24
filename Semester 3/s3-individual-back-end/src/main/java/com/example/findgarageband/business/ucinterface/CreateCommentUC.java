package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.Comment.CreateCommentRequest;
import com.example.findgarageband.domain.Comment.CreateCommentResponse;

public interface CreateCommentUC {
    CreateCommentResponse createComment(CreateCommentRequest request);
}
