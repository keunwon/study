package com.keunwon.codingtest.chapter3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem14888 {
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    private static int N;
    private static int[] numbers;
    private static final int[] operators = new int[4];


    public static void main(String[] args) throws IOException {
        input();

        dfs(numbers[0], 1);
        output();
    }

    private static void dfs(int num, int depth) {
        if (depth == N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < operators.length; i++) {
            if (operators[i] == 0) { continue; }

            operators[i]--;

            switch (i) {
                case 0: dfs( num + numbers[depth], depth + 1); break;
                case 1: dfs( num - numbers[depth], depth + 1); break;
                case 2: dfs( num * numbers[depth], depth + 1); break;
                case 3: dfs( num / numbers[depth], depth + 1); break;
            }

            operators[i] ++;
        }
    }

    private static void output() {
        System.out.println(max);
        System.out.println(min);
    }

    private static void input() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            numbers = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < operators.length; i++) {
                operators[i] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
