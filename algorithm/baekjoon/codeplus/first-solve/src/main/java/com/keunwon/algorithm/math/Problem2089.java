package com.keunwon.algorithm.math;

import java.util.Scanner;

public class Problem2089 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        if (num == 0) {
            System.out.println(0);
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (num != 1) {
            sb.append(Math.abs(num % -2));
            num = (int) Math.ceil((double) num / -2);
        }
        sb.append(num);

        System.out.println(sb.reverse());
    }
}
