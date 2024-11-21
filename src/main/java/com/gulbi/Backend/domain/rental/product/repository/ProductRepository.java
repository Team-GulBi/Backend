package com.gulbi.Backend.domain.rental.product.repository;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT p.id AS id, p.main_image AS mainImage, p.title AS title, p.price AS price FROM products p WHERE p.title LIKE CONCAT('%', :query, '%')", nativeQuery = true)
    public List<ProductOverViewResponse> findProductsByTitle(@Param("query")String query);

    @Query(value = "SELECT p.id AS id, p.main_image AS mainImage, p.title AS title, p.price AS price " +
            "FROM products p " +
            "WHERE " +
            "p.tag LIKE CONCAT('%', :query1, '%')" +
            "AND" +
            "(:query2 IS NULL OR p.tag LIKE CONCAT('%', :query2, '%'))" +
            "AND" +
            "(:query3 IS NULL OR p.tag LIKE CONCAT('%', :query3, '%') ) ", nativeQuery = true)
    public List<ProductOverViewResponse> findProductsByTag(@Param("query1") String tagQuery1, @Param("query2") String tagQuery2, @Param("query3") String tagQuery3);


    @Query("SELECT new com.gulbi.Backend.domain.rental.product.dto.product.ProductDto(p.id, p.tag, p.title, p.name, p.views, p.price, p.sido, p.sigungu, p.bname, p.description, p.rating, p.bCategory, p.mCategory, p.sCategory, p.createdAt) " +
            "FROM Product p WHERE p.id = :id")
    public Optional<ProductDto> findProductDtoById(@Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    public Optional<Product> findProductById(@Param("id") Long id);
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.views = p.views + 1 WHERE p.id = :id ")
    public void updateProductViews(@Param("id") Long id);




}
