class Solution {
    private int[][] arr;
    private int[] res;

    public int[] solution(int[][] arr) {
        this.arr = arr;
        this.res = new int[2];

        dfs(0, 0, arr.length);
        return res;
    }

    private void dfs(int r, int c, int size) {
        if (check(r, c, size)) {
            var idx = arr[r][c];
            ++res[idx];
            return;
        }

        var m = size / 2;
        dfs(r, c, m);
        dfs(r, c + m, m);
        dfs(r + m, c, m);
        dfs(r + m, c + m, m);
    }

    private boolean check(int r, int c, int size) {
        var t = arr[r][c];
        for (var i = r; i < r + size; i++) {
            for (var j = c; j < c + size; j++) {
                if (arr[i][j] != t) return false;
            }
        }
        return true;
    }
}