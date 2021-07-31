package com.keunwon.codingtest.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GreedyAlgorithm {

    public void coinFunc(int price, List<Integer> coinList) {
        int totalCoinCount = 0;
        int coinNum = 0;
        List<Integer> details = new ArrayList<>();

        for (Integer value : coinList) {
            coinNum = price / value;
            totalCoinCount += coinNum;
            price -= coinNum * value;
            details.add(coinNum);

            System.out.println(value + "원: " + coinNum + "개");
        }
        System.out.println("촏 동전 개 수: " + totalCoinCount);
    }

    public void knapsackFunc(int[][] arrs, double capacity) {
        double totalValue = 0d;
        double fraction = 0d;

        Arrays.sort(arrs, (o1, o2) -> (o2[1] / o2[0]) - (o1[1] / o1[0]));

        for (int[] arr : arrs) {
            if (capacity - arr[0] > 0) {
                capacity -= arr[0];
                totalValue += arr[1];
                System.out.println("무게: " + arr[0] + ", 가치: " + arr[1]);
            } else {
                fraction = capacity / arr[0];
                totalValue += arr[1] * fraction;
                System.out.println("무게: " + arr[0] + ", 가치: " + arr[1] + ", 비율: " + fraction);
                break;
            }
        }

        System.out.println("total value: " + totalValue);
    }

    public static void main(String ... args) {
        GreedyAlgorithm algorithm = new GreedyAlgorithm();
        int[][] doubleList = { {10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5} };

        algorithm.knapsackFunc(doubleList, 30.0);
    }
}
