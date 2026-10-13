class Solution
{
    public int solution(int [][]board)
    {
        var m = board.length;
        var n = board[0].length;
        var max = 0;

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                if (board[i][j] == 0) continue;

                if (i > 0 && j > 0) {
                    board[i][j] = 1 + Math.min(board[i - 1][j - 1], Math.min(board[i - 1][j], board[i][j - 1]));
                }
                max = Math.max(max, board[i][j]);
            }
        }
        return max * max;
    }
}