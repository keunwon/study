package com.keunwon.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem13549 {
    private static int N, K;
    private static boolean[] visited;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        input();
        bfs();
        System.out.println(min);
    }

    private static void bfs() {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(N, 0));

        while (!queue.isEmpty()) {
            Position p = queue.poll();
            visited[p.x] = true;

            if (p.x == K) {
                min = Math.min(min, p.seconds);
            }

            int[] nums = {p.x + 1, p.x - 1, p.x * 2};
            int[] times = {1, 1, 0};
            for (int i = 0; i < 3; i++) {
                int num = nums[i];

                if (0 <= num && num <= 100000 && !visited[num]) {
                    queue.offer(new Position(num, p.seconds + times[i]));
                }
            }
        }
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        visited = new boolean[100001];
    }

    private static class Position {
        int x;
        int seconds;

        Position(int x, int seconds) {
            this.x = x;
            this.seconds = seconds;
        }
    }
}
