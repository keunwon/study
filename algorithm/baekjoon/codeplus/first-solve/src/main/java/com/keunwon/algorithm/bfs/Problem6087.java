package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem6087 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int H, W;
    private static char[][] map;

    private static int min = Integer.MAX_VALUE;
    private static int[][] visisted;
    private static List<Position> destination;

    public static void main(String[] args) throws IOException {
        input();
        bfs();

        System.out.println(min);
    }

    private static void bfs() {
        var queue = new LinkedList<Position>();
        var start = destination.get(0);
        var end = destination.get(1);

        queue.offer(start);
        visisted[start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            var p = queue.poll();

            if (p.x == end.x && p.y == end.y) {
                min = Math.min(min, p.mirror);
            }

            for (int i = 0; i < 4; i++) {
                var nx = p.x + dx[i];
                var ny = p.y + dy[i];

                if (!validMap(nx, ny) || map[nx][ny] == '*') {
                    continue;
                }

                var mirror = p.mirror;
                if (p.direction != i && p.direction != - 1) {
                    mirror++;
                }

                if (visisted[nx][ny] < mirror) {
                    continue;
                }

                visisted[nx][ny] = mirror;
                queue.offer(new Position(nx, ny, mirror, i));
            }
        }
    }

    private static boolean validMap(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W;
    }

    private static void input() throws IOException {
        var st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visisted = new int[H][W];
        destination = new ArrayList<>();

        for (int i = 0; i < H; i++) {
            var line = br.readLine();

            for (int j = 0; j < W; j++) {
                var w = line.charAt(j);
                map[i][j] = w;
                visisted[i][j] = Integer.MAX_VALUE;

                if ('C' == w) {
                    destination.add(new Position(i, j, 0, -1));
                }
            }
        }
    }

    private static class Position {
        int x;
        int y;
        int mirror;
        int direction;

        Position(int x, int y, int mirror, int direction) {
            this.x = x;
            this. y = y;
            this.mirror = mirror;
            this.direction = direction;
        }
    }
}
