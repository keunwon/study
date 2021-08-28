package com.keunwon.algorithm.datastructures;

import java.util.Arrays;
import java.util.Scanner;

public class Problem10809 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        int[] numbers = new int[26];
        Arrays.fill(numbers, -1);

        // 97
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (numbers[c - 97] ==  -1) {
                numbers[c - 97] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number).append(" ");
        }

        System.out.println(sb.toString());
    }
}
