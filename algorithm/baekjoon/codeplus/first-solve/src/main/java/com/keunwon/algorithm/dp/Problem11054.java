package com.keunwon.algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Problem11054 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        final int[] rdp  = new int[n];
        for (int i = 0; i < n; i++) {
            rdp[i] = 1;

            for (int j = 0; j < n; j++) {
                if (arr[j] < arr[i]) {
                    rdp[i] = Math.max(rdp[i], rdp[j] + 1);
                }
            }
        }

        final int[] ldp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            ldp[i] = 1;

            for (int j = n - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    ldp[i] = Math.max(ldp[i], ldp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, rdp[i] + ldp[i]);
        }

        System.out.println(max - 1);
    }
}
