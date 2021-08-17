package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem16946 {

    static int N, M;
    static int[][] map, groupMap;

    final static Map<Integer, Integer> groupSize = new HashMap<>();
    final static int[][] moves = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

    public static void main(String[] args) throws IOException {
        input();

        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isGo(i, j)) {
                    groupSize.put(idx, groupCount(i, j, idx));
                    idx++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && groupMap[i][j]== 0) {
                    sb.append(moveCount(i, j));
                } else {
                    sb.append(0);
                }
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb.toString());
    }

    static int moveCount(int r, int c) {
        int sum = 1;
        Set<Integer> set = new HashSet<>();

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (validMap(nr, nc) && map[nr][nc] == 0 && groupMap[nr][nc] != 0) {
                set.add(groupMap[nr][nc]);
            }
        }

        for (Integer i : set) {
            sum += groupSize.get(i);
        }
        return sum % 10;
    }

    static int groupCount(int r, int c, int idx) {
        int count = 1;
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(r, c));
        groupMap[r][c] = idx;

        while (!queue.isEmpty()) {
            Position p = queue.poll();

            for (int[] move : moves) {
                int nr = p.x + move[0];
                int nc = p.y + move[1];

                if (validMap(nr, nc) && isGo(nr, nc)) {
                    groupMap[nr][nc] = idx;
                    queue.add(new Position(nr, nc));
                    count++;
                }
            }
        }
        return count;
    }

    static boolean validMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static boolean isGo(int r, int c) {
        return map[r][c] == 0 && groupMap[r][c] == 0;
    }
    
    static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        groupMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
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
