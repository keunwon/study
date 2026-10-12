class Solution {
    public int solution(int[][] board, int[][] skill) {
        var m = board.length;
        var n = board[0].length;
        var prefixSum = new int[m + 1][n + 1];

        for (var sk : skill) {
            var type = sk[0];
            var r1 = sk[1];
            var c1 = sk[2];
            var r2 = sk[3];
            var c2 = sk[4];
            var degree = type == 1 ? -sk[5] : sk[5];

            prefixSum[r1][c1] += degree;
            prefixSum[r1][c2 + 1] -= degree;
            prefixSum[r2 + 1][c1] -= degree;
            prefixSum[r2 + 1][c2 + 1] += degree;
        }

        for (var j = 0; j <= n; j++) {
            for (var i = 1; i <= m; i++) {
                prefixSum[i][j] += prefixSum[i - 1][j];
            }
        }

        for (var i = 0; i <= m; i++) {
            for (var j = 1; j <= n; j++) {
                prefixSum[i][j] += prefixSum[i][j - 1];
            }
        }

        var safeCount = 0;
        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                board[i][j] += prefixSum[i][j];
                if (board[i][j] > 0) ++safeCount;
            }
        }
        return safeCount;
    }
}