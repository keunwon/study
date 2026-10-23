class Solution {
    private final int[] dr = new int[] {-1, 1, 0, 0};
    private final int[] dc = new int[] {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        return dfs(board, aloc, bloc, 0).count;
    }

    private Node dfs(int[][] board, int[] p1, int[] p2, int depth) {
        var isWin = false;
        var winCount = board.length * board[0].length;
        var loseCount = depth;

        var r1 = p1[0];
        var c1 = p1[1];

        if (board[r1][c1] == 1) {
            board[r1][c1] = 0;

            for (var dir = 0; dir < 4; dir++) {
                var r = r1 + dr[dir];
                var c = c1 + dc[dir];

                if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 1) {
                    var node = dfs(board, p2, new int[] {r, c}, depth + 1);
                    isWin = isWin || !node.isWin;
                    if (!node.isWin) {
                        winCount = Math.min(winCount, node.count);
                    } else {
                        loseCount = Math.max(loseCount, node.count);
                    }
                }
            }

            board[r1][c1] = 1;
        }
        return new Node(isWin, isWin ? winCount : loseCount);
    }

    private record Node(boolean isWin, int count) {}
}