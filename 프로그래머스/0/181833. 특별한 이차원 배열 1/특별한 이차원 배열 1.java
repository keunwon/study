class Solution {
    public int[][] solution(int n) {
        var res = new int[n][n];
        for (var i = 0; i < n; i++) {
            res[i][i] = 1;
        }
        return res;
    }
}