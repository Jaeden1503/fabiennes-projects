package com.example.findgarageband.domain.Searchpost;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetPostsPaginatedResponse {
    private List<SearchPost> searchPosts;
    private Long totalPostAmount;
}
