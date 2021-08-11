package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem11725 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static Map<Integer, List<Integer>> numbers = new HashMap<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        input();

        dfs(1);

        for (int i = 2; i < N + 1; i++) {
            System.out.println(parent[i]);
        }
    }

    static void dfs(int x) {
        for (Integer value : numbers.get(x)) {
            if (parent[value] == 0) {
                parent[value] = x;
                dfs(value);
            }
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            numbers.put(i, new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());

            numbers.get(t1).add(t2);
            numbers.get(t2).add(t1);
        }
    }
}
