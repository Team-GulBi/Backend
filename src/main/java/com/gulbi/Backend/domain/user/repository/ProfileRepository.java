package com.gulbi.Backend.domain.user.repository;

import com.gulbi.Backend.domain.user.entity.Profile;
import com.gulbi.Backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // Profile 엔티티에 대한 CRUD 메소드는 JpaRepository에서 자동으로 제공.
    Optional<Profile> findByUser(User user);
}