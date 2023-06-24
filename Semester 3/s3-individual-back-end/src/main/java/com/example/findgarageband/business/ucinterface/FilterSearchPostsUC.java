package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.Searchpost.FilterSearchPostRequest;
import com.example.findgarageband.domain.Searchpost.FilterSearchPostResponse;

public interface FilterSearchPostsUC {
    FilterSearchPostResponse filterSearchPosts(FilterSearchPostRequest request);
}
