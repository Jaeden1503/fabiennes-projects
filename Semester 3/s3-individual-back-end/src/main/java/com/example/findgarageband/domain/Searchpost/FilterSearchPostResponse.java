package com.example.findgarageband.domain.Searchpost;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class FilterSearchPostResponse {
    private List<SearchPost> searchPosts;
}
