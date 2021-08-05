package com.keunwon.codingtest.chapter3;

import java.util.Arrays;
import java.util.Scanner;

public class Problem11652 {
    static int N;
    static long[] numbers;

    public static void main(String[] args) {
        input();

        Arrays.sort(numbers);

        long minValue = numbers[0];
        int maxCount = 1;
        int currentCount = 1;

        for (int i = 0; i < N - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                currentCount++;

                if (maxCount < currentCount) {
                    maxCount = currentCount;
                    minValue = numbers[i];
                }
                continue;
            }

            currentCount = 1;
        }

        System.out.println(minValue);
    }

    static void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        numbers = new long[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextLong();
        }
    }
}
