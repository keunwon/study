package com.keunwon.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem1939 {
    private static final List<List<Bridge>> bridges = new ArrayList<>();
    private static int N, M;
    private static int from, to;

    private static boolean[] visited;
    private static int left = Integer.MAX_VALUE;
    private static int right = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        binarySearch();
    }

    private static void binarySearch() {
        while (left <= right) {
            final var mid = (left + right) / 2;
            visited = new boolean[N + 1];

            if (bfs(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }

    private static boolean bfs(int weight) {
        final var queue = new LinkedList<Integer>();

        queue.offer(from);
        visited[from] = true;

        while (!queue.isEmpty()) {
            final var now = queue.poll();

            if (now == to) { return true; }

            for (Bridge bridge : bridges.get(now)) {
                if (!visited[bridge.to] && weight <= bridge.weight) {
                    queue.offer(bridge.to);
                    visited[bridge.to] = true;
                }
            }
        }
        return false;
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int i = 0; i <= N; i++) {
                bridges.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                final var landA = Integer.parseInt(st.nextToken());
                final var landB = Integer.parseInt(st.nextToken());
                final var weight = Integer.parseInt(st.nextToken());

                bridges.get(landA).add(new Bridge(landB, weight));
                bridges.get(landB).add(new Bridge(landA, weight));

                left = Math.min(left, weight);
                right = Math.max(right, weight);
            }

            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
        }
    }

    private static class Bridge {
        final int to;
        final int weight;

        Bridge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
