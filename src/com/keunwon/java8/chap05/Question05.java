package com.keunwon.java8.chap05;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Question05 {

    public static void main(String ... args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
                .map(s -> s * s)
                .forEach(System.out::println);

        List<Integer> group1 = Arrays.asList(1, 2, 3);
        List<Integer> group2 = Arrays.asList(3, 4);
        group1.stream()
              .flatMap(s -> group2.stream()
                                  .map(t -> new int[] {s, t}))
              .collect(toList());


        group1.stream()
               .flatMap(s -> group2.stream()
                                    .filter(j -> (s + j) % 3 == 0 )
                                    .map(j -> new int[] {s, j}))
               .collect(toList());
    }
}
