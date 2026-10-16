class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        var grid = new int[rows][columns];
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < columns; j++) {
                grid[i][j] = (i * columns) + j + 1;
            }
        }

        var res = new int[queries.length];
        for (var i = 0; i < queries.length; i++) {
            var q = queries[i];
            var r1 = q[0] - 1;
            var c1 = q[1] - 1;
            var r2 = q[2] - 1;
            var c2 = q[3] - 1;
            var tmp = grid[r1][c1];
            var min = tmp;

            for (var r = r1; r < r2; r++) {
                grid[r][c1] = grid[r + 1][c1];
                min = Math.min(min, grid[r][c1]);
            }

            for (var c = c1; c < c2; c++) {
                grid[r2][c] = grid[r2][c + 1];
                min = Math.min(min, grid[r2][c]);
            }

            for (var r = r2; r > r1; r--) {
                grid[r][c2] = grid[r - 1][c2];
                min = Math.min(min, grid[r][c2]);
            }

            for (var c = c2; c > c1; c--) {
                grid[r1][c] = grid[r1][c - 1];
                min = Math.min(min, grid[r1][c]);
            }

            grid[r1][c1 + 1] = tmp;
            res[i] = min;
        }
        return res;
    }
}