package com.keunwon.algorithm.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem10815 {
    private static int N, M;
    private static int[] myCards, cards;

    public static void main(String[] args) throws IOException {
        input();

        Arrays.sort(myCards);

        final var sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            final var num = cards[i];

            sb.append(hasMyCard(num) ? 1 : 0).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static boolean hasMyCard(int num) {
        var left =0;
        var right = N - 1;

        while (left <= right) {
            final var mid = (left + right) / 2;

            if (myCards[mid] == num) { return true; }

            if (myCards[mid] < num) {
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }
        return false;
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            N = Integer.parseInt(br.readLine());
            myCards = new int[N];
            var st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                myCards[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());
            cards = new int[M];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < M; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
