package com.gulbi.Backend.domain.rental.product.controller;

import com.gulbi.Backend.domain.rental.product.dto.CategoryProjection;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.repository.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController2 {
    private final CategoryRepository categoryRepository;

    public CategoryController2(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/bcategory")
    public List<Category> getBigCategory(){
        List<Category> list2 = categoryRepository.findAllNoParent();
        //List<Category> list=categoryRepository.find(1L);
        System.out.println("zz");
        return list2;
    }

    @GetMapping("/mcategory")
    public List<CategoryProjection> getMidCategory(){
        List<CategoryProjection> list = categoryRepository.findBelowCategory(1L);
        System.out.println(list);
        return list;
    }

    //1. 대분류 전부 리턴, 2. 대분류의 id값 들어오면 대응하는 모든 중분류 보여주기 3. 중분류의 id값이 들어오면 대응하는 모든 소분류 보여주기
}
