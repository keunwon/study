class Solution {
    public int solution(String[][] board, int h, int w) {
        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};
        var res = 0;

        for (var dir = 0; dir < 4; dir++) {
            var r = h + dr[dir];
            var c = w + dc[dir];

            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c].equals(board[h][w])) {
                ++res;
            }
        }
        return res;
    }
}