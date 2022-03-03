package com.keunwon.algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Problem10610 {

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var arr = sc.nextLine().toCharArray();

        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        var sum = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            var num = arr[i] - '0';
            sum += num;
            sb.append(num);
        }

        if (arr[0] != '0' || sum % 3 != 0) {
            System.out.println(-1);
            return;
        }

        System.out.println(sb.toString());
    }
}
