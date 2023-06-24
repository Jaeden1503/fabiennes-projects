package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.Searchpost.GetAllSearchPostsResponse;

public interface GetSearchPostByUserUC {
    GetAllSearchPostsResponse getAllSearchPostsByUserId(Long id);
}
