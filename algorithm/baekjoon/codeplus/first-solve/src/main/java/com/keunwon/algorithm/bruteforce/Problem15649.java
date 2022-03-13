package com.keunwon.algorithm.bruteforce;

import java.util.Scanner;

public class Problem15649 {

    public static int[] arr;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int n = sc.nextInt();
        final int m = sc.nextInt();

        arr = new int[m];
        visited = new boolean[n];

        dfs(n, m, 0);
    }

    public static void dfs(int n, int m, int depth) {
        if (m == depth) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i + 1;
                dfs(n, m, depth + 1);
                visited[i] = false;
            }
        }
    }
}
