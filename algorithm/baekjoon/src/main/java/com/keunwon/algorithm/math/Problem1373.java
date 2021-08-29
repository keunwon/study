package com.keunwon.algorithm.math;

import java.util.Scanner;

public class Problem1373 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        StringBuilder sb = new StringBuilder();

        if (input.length() % 3 == 1) {
            sb.append(input.charAt(0));
        }

        if (input.length() % 3 == 2) {
            int temp1 = (input.charAt(0) - '0') * 2;
            int temp2 = (input.charAt(1) - '0');
            sb.append(temp1 + temp2);
        }

        for (int i = input.length() % 3; i < input.length(); i += 3) {
            int temp1 = (input.charAt(i) - '0') * 4;
            int temp2 = (input.charAt(i + 1) - '0') * 2;
            int temp3 = (input.charAt(i + 2) - '0');

            sb.append(temp1 + temp2 + temp3);
        }

        System.out.println(sb.toString());
    }
}
