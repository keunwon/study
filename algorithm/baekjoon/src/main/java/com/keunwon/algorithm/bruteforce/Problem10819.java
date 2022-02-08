package com.keunwon.algorithm.bruteforce;

import java.util.Scanner;

public class Problem10819 {
    public static boolean[] visited;
    public static int[] selected, numbers;
    public static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();

        numbers = new int[N];
        selected = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        dfs(N, 0);
        System.out.println(max);
    }

    public static void dfs(int n, int depth) {
        if (n == depth) {
            max = Math.max(max, getTotal());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) { continue; }

            visited[i] = true;
            selected[depth] = numbers[i];
            dfs(n, depth + 1);
            visited[i] = false;
        }
    }

    public static int getTotal() {
        final int size = selected.length;
        int sum = 0;

        for (int i = 0; i < size - 1; i++) {
            sum += Math.abs(selected[i] - selected[i + 1]);
        }
        return sum;
    }
}
