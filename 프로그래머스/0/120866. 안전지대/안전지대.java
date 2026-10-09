import java.util.ArrayDeque;

class Solution {
    public int solution(int[][] board) {
        var dr = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
        var dc = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
        var q = new ArrayDeque<int[]>();
        var safe = 0;

        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[i].length; j++) {
                switch (board[i][j]) {
                    case 0 -> ++safe;
                    case 1 -> q.addLast(new int[] {i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            var cur = q.removeFirst();
            for (var dir = 0; dir < 8; dir++) {
                var r = cur[0] + dr[dir];
                var c = cur[1] + dc[dir];
                if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 0) {
                    board[r][c] = 1;
                    --safe;
                }
            }
        }
        return safe;
    }
}