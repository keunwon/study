package algorithm.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lesson1836 {
    private char[][] board;
    private boolean[] visited = new boolean[26];
    private Map<Character, int[][]> map = new HashMap<>();

    public String solution(int m, int n, String[] board) {
        this.board = new char[board.length][board[0].length()];

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                var type = board[i].charAt(j);
                this.board[i][j] = type;

                if (type != '.' && type != '*') {
                    if (!map.containsKey(type)) {
                        map.put(type, new int[2][2]);
                        map.get(type)[0][0] = i;
                        map.get(type)[0][1] = j;
                    } else {
                        map.get(type)[1][0] = i;
                        map.get(type)[1][1] = j;
                        Arrays.sort(map.get(type), (o1, o2) ->
                                (o1[0] != o2[0]) ? o1[0] - o2[0] : o1[1] - o2[1]);
                        visited[type - 'A'] = true;
                    }
                }
            }
        }

        var idx = 0;
        var result = new StringBuilder();

        while (!map.isEmpty()) {
            if (idx == visited.length) return "IMPOSSIBLE";

            if (!visited[idx]) {
                ++idx;
                continue;
            }

            var type = (char) ('A' + idx);
            if (check(type)) {
                var arr = map.remove(type);

                result.append(type);
                this.board[arr[0][0]][arr[0][1]] = '.';
                this.board[arr[1][0]][arr[1][1]] = '.';
                visited[idx] = false;
                idx = 0;
            } else {
                ++idx;
            }
        }

        return result.toString();
    }

    private boolean check(char type) {
        var arr = map.get(type);
        var r1 = arr[0][0];
        var c1 = arr[0][1];
        var r2 = arr[1][0];
        var c2 = arr[1][1];

        return (checkRows(r1, r2, c1, type) && checkCols(c1, c2, r2, type)) ||
                (checkRows(r1, r2, c2, type) && checkCols(c1, c2, r1, type));
    }

    private boolean checkRows(int r1, int r2, int c, char type) {
        var min = Math.min(r1, r2);
        var max = Math.max(r1, r2);

        for (var i = min; i <= max; i++) {
            if (board[i][c] != '.' && board[i][c] != type) return false;
        }
        return true;
    }

    private boolean checkCols(int c1, int c2, int r, char type) {
        var min = Math.min(c1, c2);
        var max = Math.max(c1, c2);

        for (var i = min; i <= max; i++) {
            if (board[r][i] != '.' && board[r][i] != type) return false;
        }
        return true;
    }
}
