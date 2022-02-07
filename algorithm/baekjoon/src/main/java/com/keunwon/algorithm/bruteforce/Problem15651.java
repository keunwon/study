package com.keunwon.algorithm.bruteforce;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Problem15651 {
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int[] arr;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int m = sc.nextInt();

        arr = new int[m];
        dfs(n, m, 0);

        bw.flush();
        bw.close();
    }

    public static void dfs(int n , int m, int depth) throws IOException {
        if (m == depth) {
            for (int i : arr) {
                bw.write(i + " ");
            }
            bw.write(System.lineSeparator());
            return;
        }

        for (int i = 0; i < n; i++) {
            arr[depth] = i + 1;
            dfs(n, m, depth + 1);
        }
    }
}
