package com.example.findgarageband.business.impl.Searchpost;

import com.example.findgarageband.business.ucinterface.GetAllSearchPostsUC;
import com.example.findgarageband.business.impl.SearchPostConverter;
import com.example.findgarageband.domain.Searchpost.GetAllSearchPostsResponse;
import com.example.findgarageband.domain.Searchpost.GetPostsPaginatedResponse;
import com.example.findgarageband.domain.Searchpost.SearchPost;
import com.example.findgarageband.persistence.SearchPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAllSearchPostsUCImpl implements GetAllSearchPostsUC {
    private final SearchPostRepository searchPostRepository;

    @Transactional
    @Override
    public GetAllSearchPostsResponse getAllSearchPosts() {
        List<SearchPost> searchPosts = searchPostRepository.findAll()
                .stream()
                .map(SearchPostConverter::convert)
                .toList();

        return GetAllSearchPostsResponse.builder()
                .searchPosts(searchPosts)
                .build();
    }

    @Transactional
    @Override
    public GetPostsPaginatedResponse getAllSearchPostsPaginated(int pageNumber) {
        List<SearchPost> searchPosts = searchPostRepository.findAll(Pageable.ofSize(5).withPage(pageNumber -1))
                .stream()
                .map(SearchPostConverter::convert)
                .toList();
//        System.out.println(searchPosts);
        Long amount = searchPostRepository.count();
//        System.out.println(amount);

        return GetPostsPaginatedResponse.builder()
                .searchPosts(searchPosts)
                .totalPostAmount(amount)
                .build();
    }
}
