import java.util.ArrayDeque;

class Solution {
    public int solution(String[] board) {
        var m = board.length;
        var n = board[0].length();
        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};

        var q = new ArrayDeque<Node>();
        var visited = new boolean[m][n];

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                if (board[i].charAt(j) == 'R') {
                    q.addLast(new Node(i, j, 0));
                    break;
                }
            }
        }

        while (!q.isEmpty()) {
            var cur = q.removeFirst();

            if (board[cur.r].charAt(cur.c) == 'G') {
                return cur.d;
            }

            for (var dir = 0; dir < 4; dir++) {
                var r = cur.r;
                var c = cur.c;

                while (true) {
                    r += dr[dir];
                    c += dc[dir];

                    if (r < 0 || c < 0 || r >= m || c >= n || board[r].charAt(c) == 'D') {
                        r -= dr[dir];
                        c -= dc[dir];
                        break;
                    }
                }

                if (!visited[r][c]) {
                    visited[r][c] = true;
                    q.addLast(new Node(r, c, cur.d + 1));
                }
            }
        }
        return -1;
    }

    private static class Node {
        int r;
        int c;
        int d;

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}