package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.Comment.GetAllCommentsResponse;

public interface GetCommentsByUserUC {
    GetAllCommentsResponse getCommentsByUserId(Long userId);
}
