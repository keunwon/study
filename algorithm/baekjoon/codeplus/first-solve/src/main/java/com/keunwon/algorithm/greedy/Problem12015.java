package com.keunwon.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem12015 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    private static void solution() {
        var list = new ArrayList<Integer>();
        list.add(0);

        for (int i = 0; i < N; i++) {
            if (list.get(list.size() - 1) < arr[i]) {
                list.add(arr[i]);
                continue;
            }

            var left = 1;
            var right = list.size() - 1;

            while (left < right) {
                var mid = (left + right) / 2;

                if (list.get(mid) < arr[i]) {
                    left = mid + 1;
                    continue;
                }
                right = mid;
            }

            list.set(right, arr[i]);
        }

        System.out.println(list.size() - 1);
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
