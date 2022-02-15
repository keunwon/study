package com.keunwon.algorithm.graph;

import java.io.*;
import java.util.*;

public class Problem16947 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static List<List<Integer>> subwayMap;
    private static boolean[] cycles;
    private static int[] distances;

    public static void main(String[] args) throws IOException {
        input();
        findCycleSubway();
        countNotCycleSubway();
        outputAndClose();
    }

    private static void countNotCycleSubway() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (cycles[i]) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            final int n = queue.poll();

            for (Integer num : subwayMap.get(n)) {
                if (visited[num]) { continue; }

                queue.offer(num);
                visited[num] = true;
                distances[num] = distances[n] + 1;
            }
        }
    }

    private static void findCycleSubway() {
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (dfs(-1, i, i, visited)) {
                break;
            }
            visited = new boolean[N + 1];
        }
    }

    private static boolean dfs(int pre, int now, int start, boolean[] visited) {
        visited[now] = true;

        for (Integer num : subwayMap.get(now)) {
            if (!visited[num]) {
                if (dfs(now, num, start, visited)) {
                    cycles[num] = true;
                    return true;
                }
            } else if (pre != num && num == start) {
                cycles[num] = true;
                return true;
            }
        }
        return false;
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        subwayMap = new ArrayList<>();
        distances = new int[N + 1];
        cycles = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            subwayMap.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int num1 = Integer.parseInt(st.nextToken());
            final int num2 = Integer.parseInt(st.nextToken());

            subwayMap.get(num1).add(num2);
            subwayMap.get(num2).add(num1);
        }
    }

    private static void outputAndClose() throws IOException {
        for (int i = 1; i <= N; i++) {
            bw.write(distances[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
