//package com.gulbi.Backend.domain.rental.product.util;
//
//import com.gulbi.Backend.domain.rental.product.entity.Category;
//import com.gulbi.Backend.domain.rental.product.repository.CategoryRepository;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@RequiredArgsConstructor
//@Component
//public class CategoryInitializer implements CommandLineRunner{
//    private final CategoryRepository categoryRepository;
//
//    @PostConstruct
//    private void initCategory() {
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        String[][] categories = {
//                {"식품", null},
//                {"고기", "식품"},
//                {"소고기", "고기"},
//                {"돼지고기", "고기"},
//                {"닭고기", "고기"},
//                {"가전", null},
//                {"주방가전", "가전"},
//                {"전자레인지", "주방가전"},
//                {"냉장고", "주방가전"},
//                {"청소가전", "가전"},
//                {"청소기", "청소가전"},
//                {"공기청정기", "청소가전"},
//                {"패션", null},
//                {"남성의류", "패션"},
//                {"셔츠", "남성의류"},
//                {"바지", "남성의류"},
//                {"여성의류", "패션"},
//                {"드레스", "여성의류"},
//                {"치마", "여성의류"},
//        };
//
//        for (String[] category : categories) {
//            String categoryName = category[0];
//            String parentCategoryName = category[1];
//            Category parentCategory = parentCategoryName == null
//                    ? null
//                    : categoryRepository.findByName(parentCategoryName).orElseThrow();
//            categoryRepository.save(Category.builder().name(categoryName).parent(parentCategory).build());
//        }
//    }
//    }
//
