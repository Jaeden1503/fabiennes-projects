package com.example.findgarageband.domain;

import com.example.findgarageband.domain.Searchpost.MostCommentedPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopPostsResponse {
    private List<MostCommentedPost> popularPosts;
}
