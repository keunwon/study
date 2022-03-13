package com.keunwon.algorithm.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Problem9093 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while (N -- > 0) {
            StringBuilder sb = new StringBuilder();
            Stack<Character> stack = new Stack<>();
            String input = br.readLine() + "\n";

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ' ' || input.charAt(i) == '\n') {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(" ");
                } else {
                    stack.add(input.charAt(i));
                }
            }

            System.out.println(sb.toString());
        }
    }
}
