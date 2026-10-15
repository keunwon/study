import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {
    public int solution(int[][] board) {
        var m = board.length;
        var n = board[0].length;
        var dr = new int[] {-1, 0, 1, 0};
        var dc = new int[] {0, 1, 0, -1};

        var q = new ArrayDeque<Node>();
        var dist = new int[m][n][4];

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], (int) 1e9);
            }
        }

        q.offer(new Node(0, 0, 1));
        q.offer(new Node(0, 0, 2));
        dist[0][0][1] = 0;
        dist[0][0][2] = 0;

        while (!q.isEmpty()) {
            var cur = q.removeFirst();

            if (cur.r == m - 1 && cur.c == n - 1) {
                continue;
            }

            for (var dir = 0; dir < 4; dir++) {
                var r = cur.r + dr[dir];
                var c = cur.c + dc[dir];

                if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 0) {
                    var fee = dist[cur.r][cur.c][cur.dir];
                    fee += cur.dir == dir ? 100 : 600;

                    if (dist[r][c][dir] > fee) {
                        dist[r][c][dir] = fee;
                        q.addLast(new Node(r, c, dir));
                    }
                }
            }
        }

        var min = Integer.MAX_VALUE;
        for (var i = 0; i < 4; i++) {
            min = Math.min(min, dist[m - 1][n - 1][i]);
        }
        return min;
    }

    private static class Node {
        int r;
        int c;
        int dir;

        public Node(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}