package com.keunwon.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem1697 {
    private static int N, K;
    private static int[] seconds = new int[100001];

    public static void main(String[] args) {
        input();

        if (N == K) {
            System.out.println(0);
            System.exit(0);
        }

        bfs();
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(N);
        seconds[N] = 0;

        while (!queue.isEmpty()) {
            final int num = queue.poll();

            for (int i = 0; i < 3; i++) {
                int post = num;

                if (i == 0) { post += 1; }
                else if (i == 1) { post -= 1; }
                else { post *= 2; }

                if (0 <= post && post <= 100000 && seconds[post] == 0) {
                    queue.offer(post);
                    seconds[post] = seconds[num] + 1;
                }

                if (post == K) {
                    System.out.println(seconds[post]);
                    System.exit(0);
                }
            }
        }
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
    }
}
