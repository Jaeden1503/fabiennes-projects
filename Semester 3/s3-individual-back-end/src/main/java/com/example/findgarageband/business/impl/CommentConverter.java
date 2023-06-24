package com.example.findgarageband.business.impl;

import com.example.findgarageband.domain.Comment.Comment;
import com.example.findgarageband.persistence.entity.CommentEntity;

public final class CommentConverter {
    private CommentConverter() {

    }

    //converts from comment entity to comment
    public static Comment convert(CommentEntity comment) {
        return Comment.builder()
                .id(comment.getId())
                .description(comment.getDescription())
                .date(comment.getDate())
                .user(UserConverter.convert(comment.getUserEntity()))
                .searchPost(SearchPostConverter.convert(comment.getSearchPostEntity()))
                .build();
    }
}
