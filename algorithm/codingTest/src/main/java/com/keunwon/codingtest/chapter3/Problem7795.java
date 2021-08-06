package com.keunwon.codingtest.chapter3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem7795 {
    static BufferedReader br;

    static int S;
    static int N, M;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        for (int i = 0; i < S; i++) {
            input();
            pro();
        }

        br.close();
    }

    static void pro() {
        Arrays.sort(B);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += findTargetIndex(B, A[i], 0, M - 1);
        }
        System.out.println(ans);
    }

    static int findTargetIndex(int arr[], int target, int low, int high) {
        int res = low;

        while (low <= high) {
            int m = (low + high) / 2;

            if (arr[m] < target) {
                low = m + 1;
                res = low;
            } else {
                high = m - 1;
            }
        }
        return res;
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

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
