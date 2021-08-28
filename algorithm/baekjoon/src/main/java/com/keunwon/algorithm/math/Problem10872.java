package com.keunwon.algorithm.math;

import java.util.Scanner;

public class Problem10872 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        System.out.println(factory(num));
    }

    static int factory(int num) {
        if (num == 0) {
            return 1;
        }

        return num * factory(num - 1);
    }
}
