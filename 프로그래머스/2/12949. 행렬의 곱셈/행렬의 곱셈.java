class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        var m = arr1.length;
        var n = arr2[0].length;
        var res = new int[m][n];

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < arr1[i].length; j++) {
                for (var k = 0; k < n; k++) {
                    res[i][k] += arr1[i][j] * arr2[j][k];
                }
            }
        }
        return res;
    }
}