package com.keunwon.algorithm.math;

import java.util.Scanner;

public class Problem2609 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        int result = 0;
        if (num1 > num2) {
            result = dfs(num1, num2);
        } else {
            result = dfs(num2, num1);
        }

        System.out.println(result);
        System.out.println(num1 * num2 / result);
    }

    static int dfs(int num1 , int num2) {
        if (num2 == 0) {
            return num1;
        }
        return dfs(num2, num1 % num2);
    }
}
