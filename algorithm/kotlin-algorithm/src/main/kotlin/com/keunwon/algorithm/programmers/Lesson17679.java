package com.keunwon.algorithm.programmers;

import java.util.ArrayList;

public class Lesson17679 {
    private final int[][] moves = new int[][]{{0, 1}, {1, 0}, {1, 1}};

    public int solution(int m, int n, String[] board) {
        var map = new char[m][n];
        var answer = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                map[i][j] = board[i].charAt(j);
            }
        }

        while (true) {
            var brokenCount = brokenBlocks(map);

            if (brokenCount == 0) break;

            answer += brokenCount;
            downMap(map);
        }
        return answer;
    }

    private void downMap(char[][] map) {
        for (int i = 0; i < map[0].length; i++) {
            for (int j = map.length - 1; j >= 0; j--) {
                if (map[j][i] != '.') continue;

                for (int k = j - 1; k >= 0; k--) {
                    if (map[k][i] != '.') {
                        map[j][i] = map[k][i];
                        map[k][i] = '.';
                        break;
                    }
                }
            }
        }
    }

    private int brokenBlocks(char[][] map) {
        var count = 0;
        var positions = new ArrayList<int[]>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) continue;

                var target = map[i][j];
                var flag = true;

                for (var move : moves) {
                    var nr = i + move[0];
                    var nc = j + move[1];

                    if (nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length) {
                        flag = false;
                        break;
                    }
                    if (target != map[nr][nc]) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    positions.add(new int[]{i, j});
                    for (var move : moves) {
                        var nr = i + move[0];
                        var nc = j + move[1];
                        positions.add(new int[]{nr, nc});
                    }
                }
            }
        }

        for (var arr : positions) {
            if (map[arr[0]][arr[1]] == '.') continue;

            ++count;
            map[arr[0]][arr[1]] = '.';
        }
        return count;
    }
}
