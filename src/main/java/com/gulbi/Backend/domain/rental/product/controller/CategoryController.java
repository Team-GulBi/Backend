package com.gulbi.Backend.domain.rental.product.controller;

import com.gulbi.Backend.domain.rental.product.code.CategorySuccessCode;
import com.gulbi.Backend.domain.rental.product.dto.CategoryProjection;
import com.gulbi.Backend.domain.rental.product.service.category.CategoryService;
import com.gulbi.Backend.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("/bcategory")
    public ResponseEntity<RestApiResponse> getBigCategory(){
        List<CategoryProjection> list = categoryService.getBigCategories();
        RestApiResponse response = new RestApiResponse(CategorySuccessCode.GET_CATEGORY_SUCCESS,list);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/mcategory/{categoryId}")
    public ResponseEntity<RestApiResponse> getMidCategory(@PathVariable("categoryId") Integer categoryId){
        List<CategoryProjection> list = categoryService.getBelowCategoriesByParentId(categoryId);

        RestApiResponse response = new RestApiResponse(CategorySuccessCode.GET_CATEGORY_SUCCESS,list);

        return ResponseEntity.ok(response);
    }

    //1. 대분류 전부 리턴, 2. 대분류의 id값 들어오면 대응하는 모든 중분류 보여주기 3. 중분류의 id값이 들어오면 대응하는 모든 소분류 보여주기
}
