package com.keunwon.algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem6588 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final boolean[] primesArray = createPrimesArray();

        StringBuilder sb = new StringBuilder();
        boolean hasResult = false;
        int num;
        while ((num = Integer.parseInt(br.readLine())) != 0) {
            hasResult = false;

            for (int i = 2; i < num; i++) {
                if (primesArray[i]) { continue; }

                if (!primesArray[num - i]) {
                    sb.append(String.format("%d = %d + %d\n", num, i, num - i));
                    hasResult = true;
                    break;
                }
            }

            if (!hasResult) {
                sb.append("Goldbach's conjecture is wrong.\n");
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
