package com.keunwon.algorithm.bruteforce;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Problem15652 {
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int[] arr;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        final int N = sc.nextInt();
        final int M = sc.nextInt();
        arr = new int[M];

        dfs(N, M, 0, 0);

        bw.flush();
        bw.close();
    }

    public static void dfs(int n, int m, int start, int depth) throws IOException {
        if (m == depth) {
            for (int i : arr) {
                bw.write(i + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = start; i < n; i++) {
            arr[depth] = i + 1;
            dfs(n, m, i, depth + 1);
        }
    }
}
