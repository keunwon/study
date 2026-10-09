class Solution {
    public int solution(int[][] board, int k) {
        var res = 0;
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[i].length; j++) {
                if (k >= i + j) {
                    res += board[i][j];
                }
            }
        }
        return res;
    }
}