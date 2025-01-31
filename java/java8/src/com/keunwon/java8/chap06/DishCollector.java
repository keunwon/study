package com.keunwon.java8.chap06;

import com.keunwon.java8.common.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class DishCollector {

    public static void main(String ... args) {
        // 1. counting
        long howManyDishes = Dish.menu.stream()
                                        .collect(counting());
        System.out.println(howManyDishes);

        long howManyDishes2 = Dish.menu.stream()
                                        .count();
        System.out.println(howManyDishes2);

        // 2. max
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(maxBy(dishCaloriesComparator));
        Optional<Dish> mostCalorieDish2 = Dish.menu.stream().collect(maxBy(comparing(Dish::getCalories)));
        Optional<Dish> mostCalorieDish3 = Dish.menu.stream().max(comparing(Dish::getCalories));

        System.out.println(mostCalorieDish);
        System.out.println(mostCalorieDish2);
        System.out.println(mostCalorieDish3);

        int totalCalories = Dish.menu.stream()
                                     .collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        int totalCalories2 = Dish.menu.stream()
                                      .mapToInt(Dish::getCalories)
                                      .sum();
        System.out.println(totalCalories2);

        double avgCalories = Dish.menu.stream()
                                      .collect(averagingDouble(Dish::getCalories));
        System.out.println(avgCalories);

        IntSummaryStatistics menuStatistics = Dish.menu.stream()
                                                        .collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        String shorMenu = Dish.menu.stream()
                                   .map(Dish::getName)
                                   .collect(joining(", "));
        System.out.println(shorMenu);
    }

}
