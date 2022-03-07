package com.keunwon.algorithm.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem11729 {
    private static final StringBuilder result = new StringBuilder();
    private static int N;

    public static void main(String[] args) throws IOException {
        input();

        result.append(getHanoiCount()).append(System.lineSeparator());
        hanoi(N, 1, 2, 3);

        System.out.println(result);
    }

    private static void hanoi(int n, int start, int mid, int to) {
        if (n == 1) {
            move(start, to);
            return;
        }

        hanoi(n - 1, start, to, mid);
        move(start, to);
        hanoi(n - 1, mid, start, to);
    }

    private static void move(int start, int to) {
        result.append("%d %d\n".formatted(start, to));
    }

    private static int getHanoiCount() {
        return (int) (Math.pow(2, N) - 1);
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            N = Integer.parseInt(br.readLine());
        }
    }
}
