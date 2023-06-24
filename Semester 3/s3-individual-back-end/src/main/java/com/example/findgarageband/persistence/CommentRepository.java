package com.example.findgarageband.persistence;

import com.example.findgarageband.persistence.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    CommentEntity findCommentEntityById(Long commentId);

    @Query("select a from CommentEntity a where a.searchPostEntity.id = ?1")
    List<CommentEntity> findCommentsBySearchPostId(Long searchPostId);

    @Query("select a from CommentEntity a where a.userEntity.id = ?1")
    List<CommentEntity> findCommentsByUserId(Long userId);

    @Modifying
    @Query("delete from CommentEntity a where a.searchPostEntity.id = ?1")
    void deleteCommentsBySearchPostId(Long id);
}