package com.keunwon.java8.chap06;

import com.keunwon.java8.common.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class DishParition {

    public static void main(String ... args) {
        Map<Boolean, List<Dish>> partitionMenu = Dish.menu.stream()
                                                          .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionMenu);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType
                = Dish.menu.stream()
                           .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian
                = Dish.menu.stream()
                           .collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(comparing(Dish::getCalories)), Optional::get)));
        System.out.println(mostCaloricPartitionedByVegetarian);

    }
}
