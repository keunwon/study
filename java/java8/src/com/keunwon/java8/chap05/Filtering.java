package com.keunwon.java8.chap05;

import com.keunwon.java8.common.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Filtering {

    public static void main(String ... args) {
        List<Dish> vegetarianMenu = Dish.menu.stream()
                                             .filter(Dish::isVegetarian)
                                             .collect(toList());
        vegetarianMenu.forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        List<Dish> dishesLimit3 = Dish.menu.stream()
                                            .filter(d -> d.getCalories() > 300)
                                            .limit(3)
                                            .collect(toList());
        dishesLimit3.forEach(System.out::println);


        List<Dish> dishesSkip2 = Dish.menu.stream()
                                            .filter(d -> d.getCalories() > 3000)
                                            .skip(2)
                                            .collect(toList());
        dishesSkip2.forEach(System.out::println);
    }
}
