package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem1389 {

    static int N, M;
    static List<Integer>[] graph;

    static int min = Integer.MAX_VALUE;
    static List<Integer> countList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();

        for (int i =1; i < N + 1; i++) {
            countList.add(bfs(i));
        }

        for (int i = 0; i < countList.size(); i++) {
            if (min == countList.get(i)) {
                System.out.println(i + 1);
                System.exit(0);
            }
        }
    }

    static int bfs(int x) {
        Queue<Position> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int ans = 0;

        queue.add(new Position(x, 1));
        visited[x] = true;

        while (!queue.isEmpty()) {
            Position p = queue.poll();
            List<Integer> list = graph[p.idx];

            for (Integer idx : list) {
                if (!visited[idx]) {
                    queue.add(new Position(idx, p.depth + 1));
                    visited[idx] = true;
                    ans += p.depth;
                }
            }
        }

        min = Math.min(min, ans);
        return ans;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());

            graph[t1].add(t2);
            graph[t2].add(t1);
        }
    }

    static class Position {
        int idx;
        int depth;

        public Position(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }
}
