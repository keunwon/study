package com.keunwon.algorithm.bruteforce;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Problem15657 {
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int[] numbers;
    public static int[] result;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();

        numbers = new int[N];
        result = new int[M];

        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);
        dfs(N, M, 0 , 0);

        bw.flush();
        bw.close();
    }

    public static void dfs(int n, int m, int start, int depth) throws IOException {
        if (m == depth) {
            for (int i : result) {
                bw.write(i + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = start; i < n; i++) {
            result[depth] = numbers[i];
            dfs(n, m, i, depth + 1);
        }
    }
}
