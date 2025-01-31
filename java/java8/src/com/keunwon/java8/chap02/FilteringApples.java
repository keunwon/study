package com.keunwon.java8.chap02;

import com.keunwon.java8.common.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

    public static void main(String ... args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                              new Apple(155, "green"),
                                              new Apple(120, "red"));

        List<Apple> greenApples1 = filter(inventory, new AppleGreenColorPredicate());
        System.out.println(greenApples1);

        List<Apple> heavyApples1 = filter(inventory, new AppleHeavyWeightPredicate());
        System.out.println(heavyApples1);

        List<Apple> greenApples2 = filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });

        List<Apple> heavyApples2 = filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return 150 > apple.getWeight();
            }
        });
    }


    public static List<Apple> filter(List<Apple> inventory, ApplePredicate applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (applePredicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
