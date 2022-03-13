package com.keunwon.algorithm.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Problem9012 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while (N -- > 0) {
            Stack<Character> stack = new Stack<>();
            String input = br.readLine();
            boolean isSuccess = true;

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                if (c == '(') {
                    stack.add(c);
                    continue;
                }

                if (stack.isEmpty()) {
                    isSuccess = false;
                    break;
                }

                stack.pop();
            }

            if (stack.size() != 0) {
                isSuccess = false;
            }

            System.out.println(isSuccess ? "YES" : "NO");
        }
    }
}
