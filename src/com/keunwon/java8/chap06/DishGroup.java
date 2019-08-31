package com.keunwon.java8.chap06;

import com.keunwon.java8.common.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class DishGroup {

    public static void main(String ... args) {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream()
                                                           .collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel
                = Dish.menu.stream().collect(groupingBy(dish -> {
                if (dish.getCalories() <= 400) {
                    return CaloricLevel.DIET;
                } else if (dish.getCalories() <= 700) {
                    return CaloricLevel.NORMAL;
                } else {
                    return CaloricLevel.FAT;
                }
        }));

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel
                = Dish.menu.stream().collect(
                    groupingBy(Dish::getType,
                            groupingBy(dish -> {
                                if (dish.getCalories() <= 400) {
                                    return CaloricLevel.DIET;
                                }
                                else if (dish.getCalories() <= 700) {
                                    return CaloricLevel.NORMAL;
                                }
                                else {
                                    return CaloricLevel.FAT;
                                }
                            })));

        System.out.println(dishesByTypeCaloricLevel);

        Map<Dish.Type, Long> typesCount = Dish.menu.stream()
                                                   .collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = Dish.menu.stream()
                                                                    .collect(groupingBy(Dish::getType,
                                                                                        maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByType2 = Dish.menu.stream()
                                                           .collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(mostCaloricByType2);

        Map<Dish.Type, Integer> totalCaloriesByType = Dish.menu.stream()
                                                               .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType
                = Dish.menu.stream()
                           .collect(groupingBy(Dish::getType, mapping(dish -> {
                               if (dish.getCalories() <= 400) {
                                   return CaloricLevel.DIET;
                               } else if (dish.getCalories() <= 700) {
                                   return CaloricLevel.NORMAL;
                               } else {
                                   return CaloricLevel.FAT;
                               }
                           }, toSet())));

    }

    public enum CaloricLevel { DIET, NORMAL, FAT }
}
