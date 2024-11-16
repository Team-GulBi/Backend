package com.gulbi.Backend.domain.rental.product.repository;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT p.id AS id, p.main_image AS mainImage, p.title AS title, p.price AS price FROM products p WHERE p.title LIKE CONCAT('%', :query, '%')", nativeQuery = true)
    public List<ProductOverViewResponse> findProductByQuery(@Param("query")String query);

    @Query("SELECT new com.gulbi.Backend.domain.rental.product.dto.product.ProductDto(p.id, p.tag, p.title, p.name, p.views, p.price, p.sido, p.sigungu, p.bname, p.description, p.rating, p.mainImage) " +
            "FROM Product p WHERE p.id = :id")
    ProductDto findProductDtoById(@Param("id") Long id);



}
