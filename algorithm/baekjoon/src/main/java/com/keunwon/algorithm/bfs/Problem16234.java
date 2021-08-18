package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem16234 {

    static int N;
    static int L, R;
    static int[][] map;

    static Set<Position> unions = new HashSet<>();
    static int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };


    public static void main(String[] args) throws IOException {
        input();

        int days = 0;
        while (true) {
            boolean[][] visited = new boolean[N][N];
            boolean isMove = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) {
                        continue;
                    }

                    if (bfs(i, j, visited)) {
                        isMove = true;
                    }
                }
            }

            if (isMove) { days++; }
            else { break; }
        }

        System.out.println(days);
    }

    static boolean bfs(int x, int y, boolean[][] visited) {
        Queue<Position> queue = new LinkedList<>();
        List<Position> unions = new ArrayList<>();

        queue.add(new Position(x, y));
        unions.add(new Position(x, y));
        visited[x][y] = true;

        int sum = map[x][y];
        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (!validMap(nx, ny) || visited[nx][ny]) { continue; }

                if (!isGo(map[p.x][p.y], map[nx][ny])) { continue; }

                queue.add(new Position(nx, ny));
                unions.add(new Position(nx, ny));
                sum += map[nx][ny];
                visited[nx][ny] = true;
            }
        }

        if (unions.size() == 1) { return false; }

        int temp = sum / unions.size();
        for (Position union : unions) {
            map[union.x][union.y] = temp;
        }

        return true;
    }

    static boolean isGo(int num, int nextNum) {
        int temp = Math.abs(nextNum - num);
        return L <= temp && temp <= R;
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
