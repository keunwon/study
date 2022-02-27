package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem16948 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};

    private static int N;
    private static Position start;
    private static Position end;

    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
    }

    private static void bfs() {
        var queue = new LinkedList<Position>();

        queue.offer(start);
        visited[start.r][start.c] = true;

        while (!queue.isEmpty()) {
            var p = queue.poll();

            if (p.r == end.r && p.c == end.c) {
                System.out.println(p.count);
                return;
            }

            for (int[] move : moves) {
                int nr = p.r + move[0];
                int nc = p.c + move[1];

                if (!validBoard(nr, nc) || visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                queue.offer(new Position(nr, nc, p.count + 1));
            }
        }

        System.out.println(-1);
    }

    private static boolean validBoard(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];

        var st = new StringTokenizer(br.readLine());
        var r1 = Integer.parseInt(st.nextToken());
        var c1 = Integer.parseInt(st.nextToken());
        var r2 = Integer.parseInt(st.nextToken());
        var c2 = Integer.parseInt(st.nextToken());

        start = new Position(r1, c1, 0);
        end = new Position(r2, c2, 0);
    }

    private static class Position {
        int r;
        int c;
        int count = 0;

        Position(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
}
