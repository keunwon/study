package com.keunwon.algorithm.bruteforce;

import java.util.Scanner;

public class Problem16922 {
    private static final int[] nums = {1, 5, 10, 50};
    private static int N;

    private static boolean[] visited;
    private static int result;

    public static void main(String[] args) {
        input();
        dfs(0, 0, 0);

        System.out.println(result);
    }

    private static void input() {
        final var sc = new Scanner(System.in);
        N = sc.nextInt();
        visited = new boolean[1001];
    }

    private static void dfs(int start, int sum, int depth) {
        if (N == depth) {
            if (!visited[sum]) {
                visited[sum] = true;
                result++;
            }
            return;
        }

        for (int i = start; i < 4; i++) {
            dfs(i, sum + nums[i], depth + 1);
        }
    }
}
