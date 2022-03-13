package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem16940 {
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
        bfs();
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int index = 1;

        queue.offer(index);
        visited[index] = true;

        while (!queue.isEmpty()) {
            final int n = queue.poll();

            for (Integer num : graph.get(n)) {
                if (visited[num]) { continue; }

                visited[num] = true;
                set.add(num);
            }

            for (int i = 0; i < set.size(); i++) {
                if (!set.contains(inputOrders[index])) {
                    printAndProgramFailEnd();
                }
                queue.offer(inputOrders[index]);
                index++;
            }
            set.clear();
        }

        printAndProgramSuccessEnd();
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
