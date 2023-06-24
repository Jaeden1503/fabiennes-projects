package com.example.findgarageband.persistence;

import com.example.findgarageband.domain.Searchpost.MostCommentedPost;
import com.example.findgarageband.persistence.entity.SearchPostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchPostRepository extends JpaRepository<SearchPostEntity, Long> {
    SearchPostEntity findSearchPostEntityById(Long searchPostId);

    @Query("select a from SearchPostEntity a where a.userEntity.id = ?1")
    List<SearchPostEntity> findSearchPostEntitiesByUserId(Long userId);

    @Query("select new com.example.findgarageband.domain.Searchpost.MostCommentedPost(a.searchPostEntity.id, " +
            "a.searchPostEntity.title, count(a.searchPostEntity.id) as amountComments) " +
            "from CommentEntity as a left join SearchPostEntity s on a.searchPostEntity.id = s.id " +
            "group by s.id order by amountComments desc")
    Page<MostCommentedPost> findSearchPostsWithMostComments(Pageable pageable);

    Long countBySearchingBandFalse();
    Long countBySearchingBandTrue();
    Long countByInstrument(String instrument);
}
