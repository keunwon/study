package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem16236 {

    static int N;
    static int[][] map;

    static Fish shark;
    static int sharkSize = 2;
    static int sharkEat = 0;
    static int totalSecond = 0;
    static List<Fish> feedFishes = new ArrayList<>();
    static int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        input();
        bfs();

        System.out.println(totalSecond);
    }

    static void bfs() {
        Queue<Fish> queue = new LinkedList<>();

        while (true) {
            boolean[][] visited = new boolean[N][N];

            queue.add(shark);
            map[shark.x][shark.y] = 0;
            visited[shark.x][shark.y] = true;

            while (!queue.isEmpty()) {
                Fish fish = queue.poll();

                for (int[] move : moves) {
                    int nx = fish.x + move[0];
                    int ny = fish.y + move[1];

                    if (!validMap(nx, ny) || visited[nx][ny]) {
                        continue;
                    }

                    if (map[nx][ny] == 0 || map[nx][ny] == sharkSize) {
                        queue.add(new Fish(nx, ny, fish.second + 1));
                        visited[nx][ny] = true;
                        continue;
                    }

                    if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                        queue.add(new Fish(nx, ny, fish.second + 1));
                        visited[nx][ny] = true;
                        feedFishes.add(new Fish(nx, ny, fish.second + 1));
                    }
                }
            }

            if (feedFishes.isEmpty()) {
                return;
            }

            Collections.sort(feedFishes);

            moveShark(feedFishes.get(0));
            eatFishByShark();

            feedFishes.clear();
        }
    }

    static void moveShark(Fish s) {
        shark.x = s.x;
        shark.y = s.y;

        map[s.x][s.y] = 0;
        totalSecond += s.second;
    }

    static void eatFishByShark() {
        sharkEat++;
        if (sharkSize == sharkEat) {
            sharkSize++;
            sharkEat = 0;
        }
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    shark = new Fish(i, j, 0);
                }
            }
        }
    }

    static class Fish implements Comparable<Fish> {
        int x;
        int y;
        int second;

        public Fish(int x, int y, int second) {
            this.x = x;
            this.y = y;
            this.second = second;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.second == o.second) {
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }

            return this.second - o.second;
        }
    }
}
