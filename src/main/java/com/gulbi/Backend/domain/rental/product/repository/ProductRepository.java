package com.gulbi.Backend.domain.rental.product.repository;

import com.gulbi.Backend.domain.rental.product.dto.ProductResponseDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
//    @Query("SELECT p.id AS id, p.mainImage AS mainImage, p.title AS title, p.price AS price FROM Product p WHERE p.title LIKE CONCAT('%', :query, '%')")
//@Query(value = "SELECT p.id AS id, p.main_image AS mainImage, p.title AS title, p.price AS price FROM products p WHERE p.title LIKE CONCAT('%', :query, '%')", nativeQuery = true)
//public List<ProductResponseProjection> findProductByQuery(@Param("query")String query);





//
    @Query("SELECT new com.gulbi.Backend.domain.rental.product.dto.ProductResponseDto(p.id , p.mainImage, p.title, p.price) FROM Product p WHERE p.title LIKE CONCAT('%', :query, '%')")
    public List<ProductResponseDto> findProductByQuery(@Param("query") String query);


}
