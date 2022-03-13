package com.keunwon.algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Problem1931 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] numbers = new int[n][2];

        for (int i = 0; i < n; i++) {
            numbers[i][0] = sc.nextInt();
            numbers[i][1] = sc.nextInt();
        }

        Arrays.sort(numbers, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }

            return o1[1] - o2[1];
        });

        int count = 0;
        int prevEndTime = -1;
        for (int i = 0; i < n; i++) {
            if (prevEndTime <= numbers[i][0]) {
                prevEndTime = numbers[i][1];
                count++;
            }
        }

        System.out.println(count);
    }
}
