package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class Problem16928 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static Map<Integer, Integer> moves;

    private static boolean[] visited;
    private static int[] distances;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
    }

    private static void bfs() {
        var queue = new LinkedList<Integer>();

        queue.offer(1);
        visited[1] = true;
        distances[1] = 0;

        while (!queue.isEmpty()) {
            var now = queue.poll();

            if (now == 100) {
                System.out.println(distances[now]);
                return;
            }

            for (int i = 1; i <= 6; i++) {
                var next = now + i;

                if (100 < next || visited[next]) { continue; }

                visited[next] = true;
                if (!moves.containsKey(next)) {
                    queue.offer(next);
                    distances[next] = distances[now] + 1;
                    continue;
                }

                var moveNum = moves.get(next);
                if (visited[moveNum]) { continue; }

                queue.offer(moveNum);
                visited[moveNum] = true;
                distances[moveNum] = distances[now] + 1;
            }
        }
    }

    private static void input() throws IOException {
        var st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[101];
        distances = new int[101];
        moves = new HashMap<>();

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            var key = Integer.parseInt(st.nextToken());
            var value = Integer.parseInt(st.nextToken());

            moves.put(key, value);
        }
    }
}
