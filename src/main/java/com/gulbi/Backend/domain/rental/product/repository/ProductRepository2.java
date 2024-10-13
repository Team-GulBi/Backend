package com.gulbi.Backend.domain.rental.product.repository;

import com.gulbi.Backend.domain.rental.product.dto.ProductResponseProjection;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository2 extends JpaRepository<Product,Long> {
//    @Query("SELECT p.id AS id, p.mainImage AS mainImage, p.title AS title, p.price AS price FROM Product p WHERE p.title LIKE CONCAT('%', :query, '%')")
@Query(value = "SELECT p.id AS id, p.main_image AS mainImage, p.title AS title, p.price AS price FROM products p WHERE p.title LIKE CONCAT('%', :query, '%')", nativeQuery = true)
public Optional<List<ProductResponseProjection>> findProductByQuery(@Param("query")String query);

}
