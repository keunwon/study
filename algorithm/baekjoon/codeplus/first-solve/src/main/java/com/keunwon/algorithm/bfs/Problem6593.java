package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem6593 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int L, R, C;
    static char[][][] map;
    static boolean[][][] visited;

    static Position start, end;
    static final int[][] moves =
            {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0},
            {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};

    public static void main(String[] args) throws IOException {
        while (input()) {
            int result = bfs();

            if (result == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.printf("Escaped in %d minute(s).\n", result);
            }
        }
    }

    static int bfs() {
        Queue<Position> queue = new LinkedList<>();

        queue.add(start);
        visited[start.floor][start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            if (p.floor == end.floor && p.x == end.x && p.y == end.y) {
                return p.second;
            }

            for (int[] move : moves) {
                int nf = p.floor + move[0];
                int nx = p.x + move[1];
                int ny = p.y + move[2];

                if (validMap(nf, nx, ny) && isGo(nf, nx, ny)) {
                    queue.add(new Position(nf, nx, ny, p.second + 1));
                    visited[nf][nx][ny] = true;
                }
            }
        }
        return -1;
    }

    static boolean isGo(int floor, int x, int y) {
        return map[floor][x][y] != '#' && !visited[floor][x][y];
    }

    static boolean validMap(int floor, int x, int y) {
        return 0 <= floor && floor < L && 0 <= x && x < R && 0 <= y && y < C;
    }

    static boolean input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[L][R][C];
        visited = new boolean[L][R][C];

        if (L == 0 && R == 0 && C == 0) { return false; }

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                char[] input = br.readLine().toCharArray();

                for (int k = 0; k < C; k++) {
                    map[i][j][k] = input[k];

                    if (map[i][j][k] == 'S') {
                        start = new Position(i, j, k, 0);
                    } else if (map[i][j][k] == 'E') {
                        end = new Position(i, j, k, 0);
                    }
                }
            }
            br.readLine();
        }

        return true;
    }

    static class Position {
        int floor;
        int x;
        int y;
        int second;

        public Position(int floor, int x, int y, int second) {
            this.floor = floor;
            this.x = x;
            this.y = y;
            this.second = second;
        }
    }
}
