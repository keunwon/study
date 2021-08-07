package com.keunwon.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem1202 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int bagCount;
    static int[][] gems;
    static int[] bagWeight;

    public static void main(String[] args) throws IOException {
        input();

        Arrays.sort(gems, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(bagWeight);

        long sum = 0L;
        int idx = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for (int i = 0; i < bagCount; i++) {
             while (idx < n && gems[idx][0] <= bagWeight[i]) {
                    queue.add(gems[idx][1]);
                    idx++;
             }

            if (!queue.isEmpty()) { sum += queue.poll(); }
        }

        System.out.println(sum);
    }

    private static void input() throws IOException {
        String[] arr = br.readLine().split(" ");

        n = Integer.parseInt(arr[0]);
        bagCount = Integer.parseInt(arr[1]);
        gems = new int[n][2];
        bagWeight = new int[bagCount];

        for (int i = 0; i < n; i++) {
            String[] gem = br.readLine().split(" ");

            gems[i][0] = Integer.parseInt(gem[0]);
            gems[i][1] = Integer.parseInt(gem[1]);
        }

        for (int i = 0; i < bagCount; i++) {
            bagWeight[i] = Integer.parseInt(br.readLine());
        }
    }
}
