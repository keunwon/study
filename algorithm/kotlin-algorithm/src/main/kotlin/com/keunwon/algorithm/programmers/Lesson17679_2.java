package com.keunwon.algorithm.programmers;

import java.util.ArrayList;

public class Lesson17679_2 {
    private final char MARK_TYPE = '.';

    public int solution(int m, int n, String[] board) {
        var map = new char[m][n];
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[i].length(); j++) {
                map[i][j] = board[i].charAt(j);
            }
        }

        var brokenCount = 0;
        while (true) {
            var count = markBlocks(map);

            if (count == 0) break;

            brokenCount += count;
            brokenBlocks(map);
        }
        return brokenCount;
    }

    private void brokenBlocks(char[][] map) {
        for (var i = 0; i < map[0].length; i++) {
            for (var j = map.length - 1; j >= 0; j--) {
                if (map[j][i] != MARK_TYPE) continue;

                for (var k = j - 1; k >= 0; k--) {
                    if (map[k][i] != MARK_TYPE) {
                        map[j][i] = map[k][i];
                        map[k][i] = MARK_TYPE;
                        break;
                    }
                }
            }
        }
    }

    private int markBlocks(char[][] map) {
        var moves = new int[][] {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        var blocks = new ArrayList<int[]>();

        for (var i = 0; i < map.length; i++) {
            for (var j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) continue;

                var tmpBlocks = new ArrayList<int[]>();
                for (var move : moves) {
                    var nr = i + move[0];
                    var nc = j + move[1];

                    if (nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length) continue;
                    if (map[nr][nc] != map[i][j]) continue;

                    tmpBlocks.add(new int[] {nr, nc});
                }
                if (tmpBlocks.size() == 4) blocks.addAll(tmpBlocks);
            }
        }

        var count = 0;
        for (var block : blocks) {
            if (map[block[0]][block[1]] != MARK_TYPE) {
                map[block[0]][block[1]] = MARK_TYPE;
                ++count;
            }
        }
        return count;
    }
}
