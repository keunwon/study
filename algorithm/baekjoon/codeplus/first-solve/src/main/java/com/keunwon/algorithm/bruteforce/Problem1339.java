package com.keunwon.algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem1339 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static String[] words;
    private static int[] size;

    public static void main(String[] args) throws IOException {
        input();
        groupingWordSize();
        output();
    }

    private static void groupingWordSize() {
        for (int i = 0; i < N; i++) {
            final int length = words[i].length();
            int pow = (int) Math.pow(10, length - 1);

            for (int j = 0; j < length; j++) {
                int idx = words[i].charAt(j) - 65;

                size[idx] += pow;
                pow /= 10;
            }
        }
        Arrays.sort(size);
    }

    private static void output() {
        int index = 9;
        int sum = 0;

        for (int i = size.length - 1; i >= 0; i--) {
            if (size[i] == 0) { break; }

            sum += size[i] * index;
            index--;
        }
        System.out.println(sum);
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        size = new int[26];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }
    }
}
