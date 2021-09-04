package com.keunwon.algorithm.math;

import java.util.Scanner;

public class Problem11653 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= num; i++) {
            while (num % i == 0) {
                sb.append(i).append(System.lineSeparator());
                num /= i;
            }
        }

        System.out.print(sb);
    }
}
