package com.keunwon.algorithm.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem1092 {
    static int N, M;
    static List<Integer> cranes = new ArrayList<>();
    static List<Integer> weights = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();

        cranes.sort(Comparator.reverseOrder());
        weights.sort(Comparator.reverseOrder());

        if (cranes.get(0) < weights.get(0)) {
            System.out.println(-1);
            return;
        }

        int second = 0;
        while (weights.size() > 0) {
            int cIdx = 0;
            int wIdx = 0;

            while (cIdx < N) {
                if (wIdx == weights.size()) { break; }

                if (weights.get(wIdx) <= cranes.get(cIdx)) {
                    weights.remove(wIdx);
                    cIdx++;
                } else {
                    wIdx++;
                }
            }

            second++;
        }

        System.out.println(second);
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cranes.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            weights.add(Integer.parseInt(st.nextToken()));
        }
    }
}
