package com.example.findgarageband.domain.Searchpost;

import com.example.findgarageband.domain.Comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSearchPostAndCommentsResponse {
    private SearchPost searchPost;
    private List<Comment> comments;
}
