class Solution {
    private final char[] dirs = new char[] {'d', 'l', 'r', 'u'};
    private final int[] dr = new int[] {1, 0, 0, -1};
    private final int[] dc = new int[] {0, -1, 1, 0};

    private int n = -1;
    private int m = -1;
    private int r = -1;
    private int c = -1;
    private String routeRes;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.routeRes = "";

        if (getDistance(x, y, r, c) > k) {
            return "impossible";
        }

        dfs("", x, y, k);
        return routeRes.isBlank() ? "impossible" : routeRes;
    }

    private void dfs(String route, int x, int y, int k) {
        if (!routeRes.isBlank()) return;

        var dist = getDistance(x, y, r, c);
        if (dist > k || (dist % 2 != k % 2)) return;

        if (k == 0) {
            routeRes = route;
            return;
        }

        for (var i = 0; i < 4; i++) {
            var dir = dirs[i];
            var nx = x + dr[i];
            var ny = y + dc[i];

            if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                dfs(route + dir, nx, ny, k - 1);
            }
        }
    }

    private int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}