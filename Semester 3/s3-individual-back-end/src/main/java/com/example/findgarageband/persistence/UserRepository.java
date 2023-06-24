package com.example.findgarageband.persistence;

import com.example.findgarageband.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findUserEntityById(Long userId);
    boolean existsByUsername(String username);
    @Query("select u from UserEntity u left join UserRoleEntity ur on u.id = ur.user.id " +
            "where ur.role = 'USER'")
    List<UserEntity> findAllNormalUsers();
}
