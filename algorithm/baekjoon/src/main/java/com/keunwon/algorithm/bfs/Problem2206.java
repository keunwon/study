package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem2206 {
    static int N, M;
    static int[][] map;
    static int[][] visited;

    static int min = Integer.MAX_VALUE;
    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        input();
        bfs();

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    static void bfs() {
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(0, 0, 1, 0));

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            if (p.x == N - 1 && p.y == M - 1) {
                min = Math.min(min, p.distance);
                break;
            }

            for (int[] move : moves) {
                int nx = p.x + move[0];
                int ny = p.y + move[1];

                if (validMap(nx, ny) && visited[nx][ny] > p.breakCount) {
                    if (map[nx][ny] == 0) {
                        queue.add(new Position(nx, ny, p.distance + 1, p.breakCount));
                        visited[nx][ny] = p.breakCount;
                        continue;
                    }

                    if (p.breakCount == 0) {
                        queue.add(new Position(nx, ny, p.distance + 1, p.breakCount + 1));
                        visited[nx][ny] = p.breakCount + 1;
                    }
                }
            }
        }
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        br.close();
    }

    static class Position {
        int x;
        int y;
        int distance;
        int breakCount;

        public Position(int x, int y, int distance, int breakCount) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.breakCount = breakCount;
        }
    }
}
