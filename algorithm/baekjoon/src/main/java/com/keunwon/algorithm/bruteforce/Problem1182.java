package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1182 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int S;
    private static int[] numbers;

    private static int count = 0;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);

        System.out.println(S == 0 ? --count : count);
    }

    private static void dfs(int num, int depth) {
        if (N == depth) {
            if (S == num) { count++; }
            return;
        }

        dfs(num, depth + 1);
        dfs(num + numbers[depth], depth + 1);
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }
}
