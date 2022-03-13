package com.keunwon.algorithm.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem11728 {
    private static int N, M;
    private static int[] A, B;

    public static void main(String[] args) throws IOException {
        input();

        Arrays.sort(A);
        Arrays.sort(B);

        var sb = new StringBuilder();
        var aIdx = 0;
        var bIdx = 0;

        while (aIdx < N && bIdx < M) {
            sb.append(A[aIdx] < B[bIdx] ? A[aIdx++] : B[bIdx++]);
            sb.append(" ");
        }

        while (aIdx < N) {
            sb.append(A[aIdx++]).append(" ");
        }

        while (bIdx < M) {
            sb.append(B[bIdx++]).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            var st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            A = new int[N];
            B = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
