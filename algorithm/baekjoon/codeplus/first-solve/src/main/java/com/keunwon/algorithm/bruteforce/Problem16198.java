package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem16198 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static List<Integer> numbers;

    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int sum) {
        if (numbers.size() < 3) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 1; i < numbers.size() - 1; i++) {
            final int multiple = numbers.get(i - 1) * numbers.get(i + 1);
            final int removeNum = numbers.remove(i);

            dfs(multiple + sum);
            numbers.add(i, removeNum);
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }
    }
}
