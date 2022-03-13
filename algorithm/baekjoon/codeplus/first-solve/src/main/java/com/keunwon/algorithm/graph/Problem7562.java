package com.keunwon.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem7562 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    private static int t, n;
    private static int[][] map;
    private static boolean[][] visited;
    private static Position start, destination;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            min = Integer.MAX_VALUE;
            input();
            bfs();
            System.out.println(min);
        }
    }

    private static void bfs() {
        Queue<Position> queue = new LinkedList<>();
        queue.add(start);

        if (start.x == destination.x && start.y == destination.y) {
            min = 0;
            return;
        }

        while (!queue.isEmpty()) {
            Position position = queue.poll();

            for (int[] move : moves) {
                final int nx = position.x + move[0];
                final int ny = position.y + move[1];

                if (validMap(nx, ny) && !visited[nx][ny]) {
                    queue.add(new Position(nx, ny));
                    visited[nx][ny] = true;
                    map[nx][ny] = map[position.x][position.y] + 1;
                }

                if (destination.x == nx && destination.y == ny) {
                    min = Math.min(min, map[nx][ny]);
                }
            }
        }
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        destination = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
