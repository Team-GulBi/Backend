package com.gulbi.Backend.domain.rental.product.repository;

import com.gulbi.Backend.domain.rental.product.dto.CategoryProjection;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
//    @Query("SELECT c1 FROM Category c1 LEFT JOIN c1.parent c2")
    @Query("SELECT c FROM Category c WHERE c.parent IS NULL")
    List<CategoryProjection> findAllNoParent();
    // 대분류만 꺼냄, 대분류는 부모님이 안계심

    @Query("SELECT c.id AS id, c.name AS name FROM Category c WHERE c.parent.id = :parentId")
    List<CategoryProjection> findBelowCategory(@Param("parentId") Integer parentId);
    // 중분류, 혹은 소분류만 꺼냄,
    // 대분류의 id 값이 파라미터로 들어온다면 대분류를 참고하는 중분류들만 뜰것이고,
    // 중분류의 id 값이 파라미터로 들어온다면 중분류를 참고하는 소분류들만 뜰것이다.



}
