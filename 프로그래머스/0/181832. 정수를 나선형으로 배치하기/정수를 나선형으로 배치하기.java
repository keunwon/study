class Solution {
    public int[][] solution(int n) {
        var res = new int[n][n];
        var r1 = 0;
        var c1 = 0;
        var r2 = n - 1;
        var c2 = n - 1;
        var num = 0;

        while (r1 < n && c1 < n && r2 >= 0 && c2 >= 0) {
            for (var c = c1; c <= c2; c++) {
                res[r1][c] = ++num;
            }
            ++r1;

            for (var r = r1; r <= r2; r++) {
                res[r][c2] = ++num;
            }
            --c2;

            if (r1 < n && c2 >= 0) {
                for (var c = c2; c >= c1; c--) {
                    res[r2][c] = ++num;
                }
                --r2;

                for (var r = r2; r >= r1; r--) {
                    res[r][c1] += ++num;
                }
                ++c1;
            }
        }
        return res;
    }
}