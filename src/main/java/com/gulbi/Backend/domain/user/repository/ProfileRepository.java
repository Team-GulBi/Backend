package com.gulbi.Backend.domain.user.repository;

import com.gulbi.Backend.domain.user.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // Profile 엔티티에 대한 CRUD 메소드는 JpaRepository에서 자동으로 제공.
}
