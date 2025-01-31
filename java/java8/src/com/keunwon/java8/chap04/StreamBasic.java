package com.keunwon.java8.chap04;

import com.keunwon.java8.common.Dish;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String ... args) {
        // 400 칼로리 이하의 요리 선택, 칼로리 기준으로 정렬, 요리명 추출 후 list 반환
        List<String> menuName =  Dish.menu.stream()
                                          .filter(d -> d.getCalories() > 400)
                                          .sorted(comparing(Dish::getCalories))
                                          .map(Dish::getName)
                                          .collect(toList());
        System.out.println(menuName);
    }
}
