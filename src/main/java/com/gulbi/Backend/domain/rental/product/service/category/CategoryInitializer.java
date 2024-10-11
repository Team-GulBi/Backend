package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.repository.CategoryRepository;
import com.gulbi.Backend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    @Override
    public void run(String... args) throws Exception {
        Category eat = Category.builder().name("식품").parent(null).build(); //대분류
        categoryRepository.save(eat);

        Category electric = Category.builder().name("전자기기").parent(null).build();
        categoryRepository.save(electric);

        Category food = Category.builder().name("고기").parent(eat).build(); //중분류
        categoryRepository.save(food);
        Category vegetable = Category.builder().name("채소").parent(eat).build();
        categoryRepository.save(vegetable);

        Category beef = Category.builder().name("소고기").parent(food).build(); //소분류
        categoryRepository.save(beef);
        



    }
}
