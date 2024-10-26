package com.gulbi.Backend.domain.rental.product.util;

import com.gulbi.Backend.domain.rental.product.dto.CategoryProjection;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.repository.CategoryRepository;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
@RequiredArgsConstructor
@Component
public class CreateDummyData {

    private final CategoryRepository categoryRepository;

    public String[] getRandomRegion(){
        final Map<String,List<String>> sigunguMap = new HashMap<>();
        sigunguMap.put("서울",Arrays.asList("강남구","송파구","성북구","강서구","금천구","노원구","도봉구","동작구","마포구","강동구","양천구"));
        sigunguMap.put("인천",Arrays.asList("부평구","계양구","서구","중구","미추홀구","연수구","강화군","옹진군"));
        sigunguMap.put("부산", Arrays.asList("해운대구", "사하구", "남구", "동래구", "금정구", "북구", "사상구", "수영구", "연제구", "기장군"));
        sigunguMap.put("대구", Arrays.asList("중구", "동구", "서구", "남구", "북구", "수성구", "달서구", "달성군"));
        sigunguMap.put("대전", Arrays.asList("동구", "중구", "서구", "유성구", "대덕구"));
        sigunguMap.put("광주", Arrays.asList("동구", "서구", "남구", "북구", "광산구"));
        final String[] sidoList ={"서울","인천","부산","대구","대전","광주"};

        int randomIndex = (int)(Math.random()*sidoList.length);
        String sido = sidoList[randomIndex];

        List<String> sigunguList = sigunguMap.get(sido);
        randomIndex = (int)(Math.random()*sigunguList.size());
        String sigungu = sigunguList.get(randomIndex);

        String[] region ={sido,sigungu};

        return region;
    }

    public  String getRandomProductName(){
        final String[] productName={"삼성","에플","LG"};
        final String[] productName2={"노트북","휴대폰","냉장고","세탁기","TV","이어폰","스피커","PC"};
        String randomProductName = productName[(int)(Math.random()*productName.length)];
        String randomProductName2 = productName2[(int)(Math.random()*productName2.length)];
        return randomProductName +randomProductName2;
    }

    public List<Category> getRandomCategory(){
        List<CategoryProjection> bcategoryList = categoryRepository.findAllNoParent();
        int randomIndex = (int)(Math.random()*bcategoryList.size());
        Category bcategory = (Category) bcategoryList.get(randomIndex);

        List<Category> mcategoryList = categoryRepository.findByParent(bcategory);
        randomIndex = (int)(Math.random()*mcategoryList.size());
        Category mcategory = mcategoryList.get(randomIndex);

        List<Category> scategoryList = categoryRepository.findByParent(mcategory);
        randomIndex = (int)(Math.random()*scategoryList.size());
        Category scategory = scategoryList.get(randomIndex);

        List<Category> randomCategories = Arrays.asList(bcategory,mcategory,scategory);
        return randomCategories;

    }


}
