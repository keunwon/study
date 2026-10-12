class Solution {
    private int[][] picture;

    public int[] solution(int m, int n, int[][] picture) {
        this.picture = picture;
        var count = 0;
        var max = 0;

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                if (picture[i][j] > 0) {
                    ++count;
                    max = Math.max(max, dfs(i, j, picture[i][j]));
                }
            }
        }
        return new int[] {count, max};
    }

    private int dfs(int r, int c, int target) {
        if (r < 0 || c < 0 || r >= picture.length || c >= picture[0].length || picture[r][c] != target) {
            return 0;
        }

        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};

        var count = 1;
        picture[r][c] = 0;

        for (var dir = 0; dir < 4; dir++) {
            var nr = r + dr[dir];
            var nc = c + dc[dir];
            count += dfs(nr, nc, target);
        }
        return count;
    }
}