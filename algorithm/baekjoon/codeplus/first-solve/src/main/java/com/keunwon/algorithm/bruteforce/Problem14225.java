package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem14225 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final boolean[] visited = new boolean[2000001];

    private static int N;
    private static int[] numbers;

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);
        output();
    }

    private static void dfs(int sum, int depth) {
        if (depth == N) {
            visited[sum] = true;
            return;
        }

        dfs(sum, depth + 1);
        dfs(sum + numbers[depth], depth + 1);
    }

    private static void output() {
        final int length = visited.length;

        for (int i = 1; i <= length; i++) {
            if (!visited[i]) {
                System.out.println(i);
                return;
            }
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }
}
