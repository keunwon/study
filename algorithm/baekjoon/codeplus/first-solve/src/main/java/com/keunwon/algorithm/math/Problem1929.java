package com.keunwon.algorithm.math;

import java.util.Scanner;

public class Problem1929 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        boolean[] primesArray = createPrimesArray();
        StringBuilder sb = new StringBuilder();
        for (int i = a; i <=b; i++) {
            if (i < 2) { continue; }

            if (!primesArray[i]) {
                sb.append(i).append(System.lineSeparator());
            }
        }

        System.out.println(sb.toString());
    }

    static boolean[] createPrimesArray() {
        boolean[] result = new boolean[1000001];

        for (int i = 2; i <= Math.sqrt(1000000); i++) {
            for (int j = i * 2; j <= 1000000; j += i) {
                result[j] = true;
            }
        }
        return result;
    }
}
