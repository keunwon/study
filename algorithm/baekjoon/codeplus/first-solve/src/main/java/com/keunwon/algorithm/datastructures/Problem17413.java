package com.keunwon.algorithm.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Problem17413 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        final String input = br.readLine() + "\n";

        boolean isCheck = false;
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {

            if (c == ' ' || c == '\n') {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(c);
            } else if (c == '<') {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }

                isCheck = true;
                sb.append('<');
            } else if (c == '>') {
                isCheck = false;
                sb.append('>');
            } else {
                if (isCheck) {
                    sb.append(c);
                } else {
                    stack.push(c);
                }
            }
        }

        System.out.println(sb.toString());
    }
}
