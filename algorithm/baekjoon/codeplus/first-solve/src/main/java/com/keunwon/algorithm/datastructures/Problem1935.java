package com.keunwon.algorithm.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Problem1935 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        final String input = br.readLine();
        final Map<Character, Double> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            double num = Double.parseDouble(br.readLine());
            map.put((char)(65 + i), num);
        }

        Stack<Double> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (isEng(c)) {
                stack.push(map.get(c));
                continue;
            }

            double num1 = stack.pop();
            double num2 = stack.pop();
            double ans = 0d;

            // 43(+), 45(-), 47(/), 42(*)
            switch (c) {
                case 43:
                    ans = num2 + num1;
                    break;
                case 45:
                    ans = num2 - num1;
                    break;
                case 47:
                    ans = num2 / num1;
                    break;
                case 42:
                    ans = num2 * num1;
                    break;
            }

            stack.push(ans);
        }

        System.out.printf("%.2f", stack.pop());
    }

    static boolean isEng(char c) {
        return 65 <= c && c <= 90;
    }
}
