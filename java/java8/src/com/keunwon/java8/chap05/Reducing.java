package com.keunwon.java8.chap05;

import com.keunwon.java8.common.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {

    public static void main(String ... args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream()
                         .reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sume2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sume2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        int max2 = numbers.stream().reduce(0, Integer::max);
        System.out.println(max2);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = Dish.menu.stream()
                                .map(Dish::getCalories)
                                .reduce(0, Integer::sum);
        System.out.println("Number of calories" + calories);
    }
}
