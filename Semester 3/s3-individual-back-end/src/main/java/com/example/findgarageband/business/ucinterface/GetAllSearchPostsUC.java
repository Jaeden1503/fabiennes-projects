package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.Searchpost.GetAllSearchPostsResponse;
import com.example.findgarageband.domain.Searchpost.GetPostsPaginatedResponse;

public interface GetAllSearchPostsUC {
    GetAllSearchPostsResponse getAllSearchPosts();
    GetPostsPaginatedResponse getAllSearchPostsPaginated(int pageNumber);
}
