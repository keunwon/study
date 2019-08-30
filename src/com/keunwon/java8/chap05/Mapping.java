package com.keunwon.java8.chap05;

import com.keunwon.java8.common.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Mapping {

    public static void main(String ... args) {
        List<String> dishNames = Dish.menu.stream()
                                                  .map(Dish::getName)
                                                  .collect(toList());
        System.out.println(dishNames);

        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLength = words.stream()
                                        .map(String::length)
                                        .collect(toList());
        System.out.println(wordLength);

        String[] arrayOfWords = {"Goodbyte", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        List<String> uniqueCharacters = words.stream()
                                             .map(w -> w.split(""))
                                             .flatMap(Arrays::stream)
                                             .distinct()
                                             .collect(toList());
        System.out.println(uniqueCharacters);
    }
}
