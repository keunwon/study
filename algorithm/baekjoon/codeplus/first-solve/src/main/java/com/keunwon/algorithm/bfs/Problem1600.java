package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem1600 {
    static int K, W, H;
    static int[][] map;

    final static int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    final static int[][] hoursMoves = {
            {-2, 1}, {-1, 2},
            {1, 2}, {2, 1},
            {2, 1}, {2, -1},
            {1, -2}, {-1, -2},
            {-2, -1}, {-2, -1}};

    public static void main(String[] args) throws IOException {
        input();
        bfs();
    }

    static void bfs() {
        Queue<Monkey> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[K + 1][H][W];

        queue.add(new Monkey(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Monkey m = queue.poll();

            if (!validMap(m.x, m.y)) { continue; }
            if (map[m.x][m.y] == 1) { continue; }
            if (visited[m.skill][m.x][m.y]) { continue; }

            if (m.x == H - 1 && m.y == W - 1) {
                System.out.println(m.distance);
                return;
            }

            visited[m.skill][m.x][m.y] = true;

            for (int[] move : moves) {
                int nx = m.x + move[0];
                int ny = m.y + move[1];

                queue.add(new Monkey(nx, ny, m.distance + 1, m.skill));
            }

            if (m.skill == K) { continue; }

            for (int[] hoursMove : hoursMoves) {
                int nx = m.x + hoursMove[0];
                int ny = m.y + hoursMove[1];

                queue.add(new Monkey(nx, ny, m.distance + 1, m.skill + 1));
            }
        }
        System.out.println(-1);
    }

    static boolean isGo(int x, int y, int skill,  boolean[][][] visited) {
        return map[x][y] == 0 && !visited[skill][x][y];
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Monkey {
        int x;
        int y;
        int distance;
        int skill;

        public Monkey(int x, int y, int distance, int skill) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.skill = skill;
        }
    }
}
