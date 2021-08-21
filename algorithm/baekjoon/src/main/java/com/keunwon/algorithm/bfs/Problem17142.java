package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem17142 {
   static int N, M;
   static int[][] map;
   static Virus[] activeViruses;
   static int emptyAreaCount = 0;
   static int minSecond = Integer.MAX_VALUE;

   final static List<Virus> viruses = new ArrayList<>();
   final static int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        input();

        if (emptyAreaCount == 0) {
            System.out.println(0);
        } else {
            selectedVirus(0, 0);
            System.out.println(minSecond == Integer.MAX_VALUE ? -1 : minSecond);
        }
    }

    static void selectedVirus(int idx, int depth) {
        if (depth == M) {
            infectVirus(emptyAreaCount);
            return;
        }

        for (int i = idx; i < viruses.size(); i++) {
            activeViruses[depth] = viruses.get(i);
            selectedVirus(i + 1, depth + 1);
        }
    }

    static void infectVirus(int count) {
        final Queue<Virus> queue = new LinkedList<>();
        final boolean[][] visited = new boolean[N][N];

        for (Virus activeVirus : activeViruses) {
            queue.add(activeVirus);
            visited[activeVirus.x][activeVirus.y] = true;
        }

        while (!queue.isEmpty()) {
            Virus v = queue.poll();

            for (int[] move : moves) {
                int nx = v.x + move[0];
                int ny = v.y + move[1];

                if (!validMap(nx, ny)) { continue; }
                if (visited[nx][ny] || map[nx][ny] == 1) { continue; }

                if (map[nx][ny] == 0) {
                    count--;
                }

                if (count == 0) {
                    minSecond = Math.min(minSecond, v.second + 1);
                    return;
                }

                visited[nx][ny] = true;
                queue.add(new Virus(nx, ny, v.second + 1));
            }
        }
    }

    static boolean validMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        activeViruses = new Virus[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 0) {
                    emptyAreaCount++;
                } else if (map[i][j] == 2) {
                    viruses.add(new Virus(i, j, 0));
                }
            }
        }
    }

    static class Virus {
       int x;
       int y;
       int second;

        public Virus(int x, int y, int second) {
            this.x = x;
            this.y = y;
            this.second = second;
        }
    }
}
