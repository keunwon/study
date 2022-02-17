package com.keunwon.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Problem13913 {
    private static int N, K;

    private static int[] parents;
    private static int[] seconds;

    public static void main(String[] args) {
        input();
        bfs();
        output();
    }

    private static void output() {
        Stack<Integer> stack = new Stack<>();
        int idx = K;
        stack.push(K);

        while (idx != N) {
            stack.push(parents[idx]);
            idx = parents[idx];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(seconds[K]).append(System.lineSeparator());
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(N);

        while (!queue.isEmpty()) {
            final int num = queue.poll();

            for (int i = 0; i < 3; i++) {
                int post = num;

                if (i == 0) { post += 1; }
                else if (i == 1) { post -= 1; }
                else { post *= 2; }

                if (0 <= post && post <= 100000 && seconds[post] == 0) {
                    parents[post] = num;
                    seconds[post] = seconds[num] + 1;
                    queue.offer(post);
                }

                if (num == K) { return; }
            }
        }
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        seconds = new int[100001];
        parents = new int[100001];
    }
}
