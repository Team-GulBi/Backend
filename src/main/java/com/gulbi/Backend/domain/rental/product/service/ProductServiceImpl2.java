package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.ProductRegisterRequest;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.CategoryRepository;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository2;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl2 implements ProductService2 {

    private final ProductRepository2 productRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository; // user관련 태호가 머지해주기 전에는 임시로 만들어서 쓸거임. 추후 머지 되면 없어짐.
    @Override
    public void registerProduct(ProductRegisterRequest product) {
        String productName = product.getProductName();
        String title = product.getTitle();
        User user = User.builder().email("1").phoneNumber("2").nickname("z").password("2").build();  // user관련 태호가 머지해주기 전에는 임시로 만들어서 쓸거임. 추후 머지 되면 없어짐.
        userRepository.save(user); // user관련 태호가 머지해주기 전에는 임시로 만들어서 쓸거임. 추후 머지 되면 없어짐.
        String bname = product.getBname();
        String sido = product.getSido();
        String sigungu = product.getSigungu();
        Integer price = Integer.parseInt(product.getPrice());
        String tag = product.getTag();
        String description = product.getDescription();

        Integer scategoryId = Integer.parseInt(product.getScategoryId());
        Integer mcategoryId = Integer.parseInt(product.getMcategoryId());
        Integer bcategoryId = Integer.parseInt(product.getBcategoryId());

        Optional<Category> scategory = categoryRepository.findById(scategoryId);
        Optional<Category> mcategory = categoryRepository.findById(mcategoryId);
        Optional<Category> bcategory = categoryRepository.findById(bcategoryId);

        if(!scategory.isPresent() || !mcategory.isPresent() || !bcategory.isPresent()){
            System.out.println("해당 카테고리는 존재하지 않습니다.");
            //예외처리 하기
        }

        Product product1 = Product.builder()
                .user(user)
                .name(productName)
                .price(price)
                .tag(tag)
                .bname(bname)
                .sido(sido)
                .sigungu(sigungu)
                .description(description)
                .title(title)
                .views(0)
                .rating(0)
                .bCategory(bcategory.get())
                .mCategory(mcategory.get())
                .sCategory(scategory.get())
                .build();


        productRepository.save(product1);

    }
}
