package com.keunwon.algorithm.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem2668 {

    static int N;
    static int[] numbers;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        input();

        for (int i = 1; i <= N; i++) {
            dfs(i, i, new boolean[N + 1]);
        }

        Collections.sort(result);

        System.out.println(result.size());
        for (Integer value : result) {
            System.out.println(value);
        }
    }

    static void dfs(int x, int target, boolean[] visited) {
        if (target == numbers[x]) {
            result.add(target);
        }

        visited[x]  = true;

        if (!visited[numbers[x]]) {
            dfs(numbers[x], target, visited);
        }
    }

    static void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        numbers = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            numbers[i] = sc.nextInt();
        }
    }
}
