package com.keunwon.algorithm.bruteforce;

import java.util.Scanner;

public class Problem16968 {

    public static void main(String[] args) {
        final var sc = new Scanner(System.in);
        final var arr = sc.nextLine().toCharArray();
        var result = 1;

        for (int i = 0; i < arr.length; i++) {
            var num = 'c' == arr[i] ? 26 : 10;

            if (0 < i && arr[i] == arr[i - 1]) {
                num--;
            }
            result *= num;
        }

        System.out.println(result);
    }
}
