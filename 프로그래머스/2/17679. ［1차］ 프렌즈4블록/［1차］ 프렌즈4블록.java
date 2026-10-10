import java.util.ArrayDeque;

class Solution {
    public int solution(int m, int n, String[] board) {
        var dr = new int[] {0, 0, 1, 1};
        var dc = new int[] {0, 1, 0, 1};
        var grid = new char[m][n];

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                grid[i][j] = board[i].charAt(j);
            }
        }

        var q = new ArrayDeque<int[]>();
        var res = 0;

        while (true) {
            for (var i = 0; i < m - 1; i++) {
                for (var j = 0; j < n - 1; j++) {
                    var t = grid[i][j];
                    var valid = true;

                    if (t == '.') {
                        continue;
                    }

                    for (var dir = 0; dir < 4; dir++) {
                        var r = i + dr[dir];
                        var c = j + dc[dir];
                        if (grid[r][c] != t) {
                            valid = false;
                            break;
                        }
                    }

                    if (valid) q.addLast(new int[] {i, j});
                }
            }

            if (q.isEmpty()) {
                break;
            }

            while (!q.isEmpty()) {
                var cur = q.removeFirst();
                for (var dir = 0; dir < 4; dir++) {
                    var r = cur[0] + dr[dir];
                    var c = cur[1] + dc[dir];
                    
                    if (grid[r][c] != '.') {
                        grid[r][c] = '.';
                        ++res;
                    }
                }
            }

            for (var j = 0; j < n; j++) {
                for (var i = m - 1; i >= 0; i--) {
                    if (grid[i][j] != '.') continue;

                    for (var k = i - 1; k >= 0; k--) {
                        if (grid[k][j] != '.') {
                            grid[i][j] = grid[k][j];
                            grid[k][j] = '.';
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
}