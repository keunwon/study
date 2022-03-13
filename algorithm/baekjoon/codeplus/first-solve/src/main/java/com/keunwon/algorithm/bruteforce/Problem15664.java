package com.keunwon.algorithm.bruteforce;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem15664 {
    public static final Set<String> set = new LinkedHashSet<>();

    public static int[] numbers;
    public static int[] result;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();

        numbers = new int[N];
        visited = new boolean[N];
        result = new int[M];

        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);
        dfs(N, M, 0,0);

        set.forEach(System.out::println);
    }

    public static void dfs(int n, int m, int start, int depth) {
        if (m == depth) {
            StringBuilder sb = new StringBuilder();
            for (int i : result) {
                sb.append(i).append(" ");
            }
            set.add(sb.toString());
            return;
        }

        for (int i = start; i < n; i++) {
            if (visited[i]) { continue; }

            visited[i] = true;
            result[depth] = numbers[i];
            dfs(n, m, i, depth + 1);
            visited[i] = false;
        }
    }
}
