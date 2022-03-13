package com.keunwon.algorithm.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem10828 {
    static int[] stack;
    static int size = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        stack = new int[N + 1];

        while (N -- > 0) {
            String[] command = br.readLine().split(" ");

            switch (command[0]) {
                case "push":
                    push(Integer.parseInt(command[1]));
                    break;
                case "pop":
                    System.out.println(pop());
                    break;
                case "size":
                    System.out.println(size());
                    break;
                case "empty":
                    System.out.println(empty());
                    break;
                case "top":
                    System.out.println(top());
                    break;
            }
        }
    }

    static void push(int num) {
        stack[++size] = num;
    }

    static int pop() {
        if (size == 0) {
            return -1;
        }
        return stack[size--];
    }

    static int size() {
        return size;
    }

    static int empty() {
        return size == 0 ? 1 : 0;
    }

    static int top() {
        if (size == 0) {
            return -1;
        }
        return stack[size];
    }
}
