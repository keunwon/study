package com.keunwon.algorithm.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem10820 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        while ((input = br.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            int[] numbers = new int[4];

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                if (isLowerCase(c)) {
                    numbers[0]++;
                } else if (isUpperCase(c)) {
                    numbers[1]++;
                } else if (c == ' ') {
                    numbers[3]++;
                } else {
                    numbers[2]++;
                }
            }

            for (int i = 0; i < 4; i++) {
                sb.append(numbers[i]).append(" ");
            }
            System.out.println(sb.toString());
        }
    }

    static boolean isUpperCase(char c) {
        return 65 <= c && c <= 90;
    }

    static boolean isLowerCase(char c) {
        return 97 <= c && c <= 122;
    }
}
