package com.gulbi.Backend.domain.rental.recommandation.vo;

import com.gulbi.Backend.domain.rental.recommandation.code.QueryFilter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class PersonalRecommendationRequestDto {
        private int size;
        private boolean pagination;
        private QueryFilter filter;
        private Map<Integer, Map<String, Value>> categories;

        public PersonalRecommendationRequestDto() {
            this.size = 0;
            this.pagination = false;
            this.filter = null;
        }

        public PersonalRecommendationRequestDto(int size, boolean isPagination, QueryFilter filter) {
            this.size = size;
            this.pagination = isPagination;
            this.filter = filter;
        }

        @Getter
        public static class Value {
            private final Integer intValue;
            private final LocalDateTime dateTimeValue;

            public Value() {
                this.intValue = null;
                this.dateTimeValue = null;
            }

            @JsonCreator
            public Value(@JsonProperty("intValue") Integer intValue,
                         @JsonProperty("dateTimeValue") LocalDateTime dateTimeValue) {
                this.intValue = intValue;
                this.dateTimeValue = dateTimeValue;
            }
        }

//    public void print() {
//        if (categories != null && !categories.isEmpty()) {
//            categories.forEach((priority, valueMap) -> {
//                System.out.println("Priority: " + priority);
//                valueMap.forEach((keyString, value) -> {
//                    System.out.print("  " + keyString + ": ");
//                    if (value.getIntValue() != null) {
//                        System.out.println(value.getIntValue());
//                    } else if (value.getDateTimeValue() != null) {
//                        System.out.println(value.getDateTimeValue());
//                    } else {
//                        System.out.println("null");
//                    }
//                });
//            });
//        } else {
//            System.out.println("Categories is empty.");
//        }
//    }

}


