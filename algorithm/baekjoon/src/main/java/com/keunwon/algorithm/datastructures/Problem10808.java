package com.keunwon.algorithm.datastructures;

import java.util.Scanner;

public class Problem10808 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int[] numbers = new int[26];

        for (char c : input.toCharArray()) {
            numbers[c - 97]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : numbers) {
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }
}
