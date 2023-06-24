package com.example.findgarageband.domain.Comment;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllCommentsResponse {
    List<Comment> comments;
}
