package com.keunwon.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem194 {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            findNewRecruits();
        }

        br.close();
    }

    static void findNewRecruits() throws IOException {
        int o = Integer.parseInt(br.readLine());

        int[][] numbers = new int[o][2];
        for (int i = 0; i < o; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            numbers[i][0] = Integer.parseInt(st.nextToken());
            numbers[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers, (o1, o2) -> o1[0] - o2[0]);

        int rank = numbers[0][1];
        int count = 1;
        for (int i = 1; i < o; i++) {
            if (rank > numbers[i][1]) {
                rank = numbers[i][1];
                count++;
            }
        }

        System.out.println(count);
    }
}
