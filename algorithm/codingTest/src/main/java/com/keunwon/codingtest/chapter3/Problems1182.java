package com.keunwon.codingtest.chapter3;

public class Problems1182 {
    private static int N, S, COUNT;
    private static int numbers[];


    public static void main(String[] args) {

    }

    private static void dfs(int depth, int value) {
        if (depth == N) {
            if (S == value) { COUNT++; }
            return;
        }

        dfs(depth + 1, numbers[depth + 1] + value);
        dfs(depth + 1, value);
    }
}
