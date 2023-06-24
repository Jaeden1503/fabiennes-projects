package com.example.findgarageband.domain.Searchpost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MostCommentedPost {
    private Long searchPostId;
    private String searchPostTitle;
    private Long amountComments;
}
