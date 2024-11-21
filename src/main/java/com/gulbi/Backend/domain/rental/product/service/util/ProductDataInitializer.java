package com.gulbi.Backend.domain.rental.product.service.util;

import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import com.gulbi.Backend.domain.rental.review.entity.Review;
import com.gulbi.Backend.domain.rental.review.repository.ReviewRepository;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ProductDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CreateDummyData createDummyData;
    private final ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {
        long startTime = System.currentTimeMillis();
//        List<User> testUsers = Arrays.asList(
//                User.builder().nickname("testUser1").email("123@naver.com").phoneNumber("010-0000-0000").password("as1!").build(),
//                User.builder().nickname("testUser2").email("124@naver.com").phoneNumber("010-0000-0001").password("as2!").build(),
//                User.builder().nickname("testUser3").email("125@naver.com").phoneNumber("010-0000-0002").password("as3!").build(),
//                User.builder().nickname("testUser4").email("126@naver.com").phoneNumber("010-0000-0003").password("as4!").build(),
//                User.builder().nickname("testUser5").email("127@naver.com").phoneNumber("010-0000-0004").password("as5!").build()
//                );
//        userRepository.saveAll(testUsers);

        List<User> testUsers = userRepository.findAll();
        List<Product> products = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();

        for (User user : testUsers) {
            for (int i = 0; i < 100; i++) {
                String productName = createDummyData.getRandomProductName();
                String[] region = createDummyData.getRandomRegion();
                String sido = region[0];
                String sigungu = region[1];
                List<Category> categories = createDummyData.getRandomCategory();
                Category bcategory = categories.get(0);
                Category mcategory = categories.get(1);
                Category scategory = categories.get(2);
                String tags = createDummyData.getRandomTag();
                Integer price = createDummyData.getRandomPrice();

                Product product = Product.builder()
                        .user(user)
                        .name(productName)
                        .bCategory(bcategory)
                        .mCategory(mcategory)
                        .sCategory(scategory)
                        .sido(sido)
                        .sigungu(sigungu)
                        .bname("동동")
                        .description("야무짐")
                        .title(productName)
                        .tag(tags)
                        .price(price)
                        .views(0)
                        .rating(0)
                        .build();

                products.add(product);
                createDummyData.setRandomReview(product, user);

            }
        }

        productRepository.saveAll(products);
        reviewRepository.saveAll(createDummyData.getReviews());

        long endTime = System.currentTimeMillis(); // 종료 시간 기록
        long duration = endTime - startTime; // 실행 시간 계산

        System.out.println("Total execution time: " + duration + " milliseconds");
        System.out.println("Total execution time: " + duration + " milliseconds");
        System.out.println("Total execution time: " + duration + " milliseconds");
        System.out.println("Total execution time: " + duration + " milliseconds");
        System.out.println("Total execution time: " + duration + " milliseconds");
        System.out.println("Total execution time: " + duration + " milliseconds");
        System.out.println("Total execution time: " + duration + " milliseconds");

    }


}
