package com.keunwon.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Problem1981 {
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int n;
    private static int[][] map;

    private static int min = 200;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        input();
        binarySearch();
    }

    private static void binarySearch() {
        var left = 0;
        var right = max - min;

        while (left <= right) {
            final var mid = (left + right) / 2;

            if (bfs(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(right + 1);
    }

    private static boolean bfs(int diff) {
        for (int start = min; start + diff <= max; start++) {
            final var end = start + diff;

            if (map[0][0] < start || end < map[0][0]) {
                continue;
            }

            final var queue = new ArrayDeque<Point>();
            final var visited = new boolean[n][n];

            queue.offer(new Point(0, 0));
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                final var now = queue.poll();

                if (now.x == n - 1 && now.y == n - 1) {
                    return true;
                }

                for (int[] move : moves) {
                    final var nx = now.x + move[0];
                    final var ny = now.y + move[1];

                    if (!validMap(nx, ny) || visited[nx][ny]) {
                        continue;
                    }

                    if (start <= map[nx][ny] && map[nx][ny] <= end) {
                        queue.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                final var st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    final var num = Integer.parseInt(st.nextToken());

                    map[i][j] = num;
                    min = Math.min(min, num);
                    max = Math.max(max, num);
                }
            }
        }
    }

    private static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
