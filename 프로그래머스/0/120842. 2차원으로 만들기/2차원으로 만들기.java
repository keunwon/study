class Solution {
    public int[][] solution(int[] num_list, int n) {
        var m = num_list.length / n;
        var res = new int[m][n];

        for (var i = 0; i < m; i++) {
            System.arraycopy(num_list, i * n, res[i], 0, n);
        }
        return res;
    }
}