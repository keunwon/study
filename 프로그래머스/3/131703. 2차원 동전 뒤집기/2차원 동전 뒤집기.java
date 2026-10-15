class Solution {
    private int res = Integer.MAX_VALUE;

    public int solution(int[][] beginning, int[][] target) {
        dfs(beginning, target, 0, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void dfs(int[][] beginning, int[][] target, int count, int depth) {
        if (depth == beginning.length) {
            var m = beginning.length;
            var n = beginning[0].length;
            var totalCount = count;

            for (var i = 0; i < n; i++) {
                var x = 0;
                for (var j = 0; j < m; j++) {
                    if (beginning[j][i] == target[j][i]) {
                        ++x;
                    }
                }

                if (x == 0) {
                    ++totalCount;
                } else if (x != m) {
                    totalCount = -1;
                    break;
                }
            }

            if (totalCount > -1) {
                res = Math.min(res, totalCount);
            }
            return;
        }

        turn(beginning, depth);
        dfs(beginning, target, count + 1, depth + 1);

        turn(beginning, depth);
        dfs(beginning, target, count, depth + 1);
    }

    private void turn(int[][] board, int row) {
        for (var i = 0; i < board[row].length; i++) {
            board[row][i] ^= 1;
        }
    }
}