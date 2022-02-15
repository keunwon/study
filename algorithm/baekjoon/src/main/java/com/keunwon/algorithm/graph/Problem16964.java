package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem16964 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static int[] inputOrders;

    public static void main(String[] args) throws IOException {
        input();

        if (inputOrders[0] != 1) {
            printAndProgramFailEnd();
        }

        dfs(1, 0);
        System.out.println(1);
    }

    private static void dfs(int x, int index) {
        Set<Integer> set = new HashSet<>();

        for (Integer num : graph.get(x)) {
            if (!visited[num]) {
                visited[num] = true;
                set.add(num);
            }
        }

        for (int i = 0; i < set.size(); i++) {
            if (!set.contains(inputOrders[index])) {
                printAndProgramFailEnd();
            }
            dfs(inputOrders[index], index + 1);
        }
        set.clear();
    }

    private static void printAndProgramSuccessEnd() {
        System.out.println(1);
        System.exit(0);
    }

    private static void printAndProgramFailEnd() {
        System.out.println(0);
        System.exit(0);
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        visited = new boolean[N + 1];
        inputOrders = new int[N];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int num1 = Integer.parseInt(st.nextToken());
            final int num2 = Integer.parseInt(st.nextToken());

            graph.get(num1).add(num2);
            graph.get(num2).add(num1);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputOrders[i] = Integer.parseInt(st.nextToken());
        }
    }
}
