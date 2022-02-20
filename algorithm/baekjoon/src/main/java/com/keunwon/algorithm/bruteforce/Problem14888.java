package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem14888 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] numbers;
    private static int[] operations;

    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        dfs(numbers[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int num, int depth) {
        if (N == depth) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operations[i] > 0) {
                operations[i]--;

                final int depthNum = numbers[depth];
                switch (i) {
                    case 0: dfs(num + depthNum, depth + 1); break;
                    case 1: dfs(num - depthNum, depth + 1); break;
                    case 2: dfs(num * depthNum, depth + 1); break;
                    case 3: dfs(num / depthNum, depth + 1); break;
                }

                operations[i]++;
            }
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        operations = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operations[i] = Integer.parseInt(st.nextToken());
        }
    }
}
