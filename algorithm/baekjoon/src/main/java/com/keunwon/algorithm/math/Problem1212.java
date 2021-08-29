package com.keunwon.algorithm.math;

import java.util.Scanner;

public class Problem1212 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String input = sc.nextLine();

        for (int i = 0; i < input.length(); i++) {
            String num = Integer.toBinaryString(input.charAt(i) - '0');

            if (i != 0 && num.length() < 3) {
                num = String.format("%3s", num).replaceAll(" ", "0");
            }
            sb.append(num);
        }

        System.out.println(sb.toString());
    }
}
