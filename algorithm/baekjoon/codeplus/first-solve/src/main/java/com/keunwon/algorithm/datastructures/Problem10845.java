package com.keunwon.algorithm.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Problem10845 {
    static List<Integer> queue = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            String[] input = br.readLine().split(" ");

            switch (input[0]) {
                case "push":
                    push(Integer.parseInt(input[1]));
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
                case "front":
                    System.out.println(front());
                    break;
                case "back":
                    System.out.println(back());
                    break;
            }
        }
    }

    static void push(int num) {
        queue.add(num);
    }

    static int pop() {
        return queue.isEmpty() ? -1 : queue.remove(0);
    }

    static int size() {
        return queue.size();
    }

    static int empty() {
        return queue.isEmpty() ? 1 : 0;
    }

    static int front() {
        return queue.isEmpty() ? -1 : queue.get(0);
    }

    static int back() {
        return queue.isEmpty() ? -1 : queue.get(queue.size() - 1);
    }
}
