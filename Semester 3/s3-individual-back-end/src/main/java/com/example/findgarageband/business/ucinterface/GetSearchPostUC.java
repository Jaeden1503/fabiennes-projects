package com.example.findgarageband.business.ucinterface;

import com.example.findgarageband.domain.Searchpost.GetSearchPostAndCommentsResponse;
import com.example.findgarageband.domain.Searchpost.SearchPost;

public interface GetSearchPostUC {
    GetSearchPostAndCommentsResponse getSearchPostAndComments(Long searchPostId);
    SearchPost getSearchPost(Long id);
}
