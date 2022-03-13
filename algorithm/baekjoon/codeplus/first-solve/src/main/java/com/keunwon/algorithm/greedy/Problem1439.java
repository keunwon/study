package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem1439 {
    static int[] numbers;

    public static void main(String[] args) {
        input();

        int zeroCount = 0;
        int oneCount = 0;

        if (numbers[0] == 0) { zeroCount++; }
        else { oneCount++; }

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                continue;
            }

            if (numbers[i + 1] == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
        }

        System.out.println(Math.min(zeroCount, oneCount));
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        String world = sc.nextLine();
        numbers = new int[world.length()];

        for (int i = 0; i < world.length(); i++) {
            numbers[i] = Integer.parseInt(world.substring(i, i + 1));
        }
    }
}
