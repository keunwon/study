package com.keunwon.algorithm.datastructures;

import java.util.Scanner;
import java.util.Stack;

public class Problem10799 {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final String input = sc.nextLine();
        Stack<Character> stack = new Stack<>();

        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);

            if (c == '(') {
                stack.push('(');
                continue;
            }

            if (c == ')') {
                stack.pop();

                if (input.charAt(i  - 1) == '(') {
                    result += stack.size();
                } else {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
