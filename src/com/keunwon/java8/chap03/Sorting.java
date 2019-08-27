package com.keunwon.java8.chap03;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;

public class Sorting {

    public static void main(String ... args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red")));

        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(comparing((a) -> a.getWeight()));
        inventory.sort(comparing(Apple::getWeight));

        // 역정렬
        inventory.sort(comparing(Apple::getWeight).reversed());

        // sort 비교한 결과가 같을때 다음조건으로 정렬
        inventory.sort(comparing(Apple::getWeight)
                  .reversed()
                  .thenComparing(Apple::getColor));
    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(Integer weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

}
