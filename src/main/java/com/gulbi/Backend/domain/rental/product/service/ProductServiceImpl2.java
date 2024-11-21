package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.ProductDetailResponse;
import com.gulbi.Backend.domain.rental.product.dto.ProductRegisterRequest;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.CategoryRepository;
import com.gulbi.Backend.domain.rental.product.repository.ImageRepository;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository2;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import com.gulbi.Backend.global.util.Base64Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl2 implements ProductService2 {

    private final ProductRepository2 productRepository;

    private final ImageService imageService;

    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;

    private final UserRepository userRepository; // user관련 태호가 머지해주기 전에는 임시로 만들어서 쓸거임. 추후 머지 되면 없어짐.
    @Override
    public void registerProduct(ProductRegisterRequest product, List<MultipartFile> images) {

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

       try{
            String mainImg = Base64Util.MultipartFileToString(images.get(0));


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
                .mainImage(mainImg)
                .build();


        productRepository.save(product1);

        imageService.registerImageWithProduct(images,product1);
       }catch (IOException e){
           System.out.println(e);
       }


    }

    @Override
    public ProductDetailResponse getProductDetail(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException());
        List<Image> imageList = imageRepository.findByImageWithProduct(product.getId()).orElseThrow(()->new RuntimeException());

        final String tag = product.getTag();
        final String title = product.getTitle();
        final String productName = product.getName();
        final String price = String.valueOf(product.getPrice());
        final String views = String.valueOf(product.getViews());
        final String sido = product.getSido();
        final String sigungu = product.getSigungu();
        final String bname = product.getBname();
        final String description = product.getDescription();
        final String rationg = String.valueOf(product.getRating());
        final String bCategory= product.getBCategory().getName();
        final String mcategory = product.getMCategory().getName();
        final String scategory = product.getSCategory().getName();
        final String created_at = String.valueOf(product.getCreatedAt());


        final List<String> images = new ArrayList<>();
        for (Image image: imageList) {
            images.add(image.getUrl());
        }

        ProductDetailResponse response = ProductDetailResponse.builder()
                .tag(tag)
                .title(title)
                .productName(productName)
                .price(price)
                .view(views)
                .sido(sido)
                .sigungu(sigungu)
                .bname(bname)
                .description(description)
                .rating(rationg)
                .bcategory(bCategory)
                .mcategory(mcategory)
                .scategory(scategory)
                .created_at(created_at)
                .images(images).build();


    return  response;
    }


}
