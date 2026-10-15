class Solution {
    public int solution(int n, int[][] results) {
        var dist = new int[n + 1][n + 1];

        for (var r : results) {
            var u = r[0];
            var v = r[1];

            dist[u][v] = 1;
            dist[v][u] = -1;
        }

        for (var i = 1; i <= n; i++) {
            for (var j = 1; j <= n; j++) {
                for (var k = 1; k <= n; k++) {
                    if (dist[j][i] == 1 && dist[i][k] == 1) {
                        dist[j][k] = 1;
                        dist[k][j] = -1;
                    } else if (dist[j][i] == -1 && dist[i][k] == -1) {
                        dist[j][k] = -1;
                        dist[k][j] = 1;
                    }
                }
            }
        }

        var res = 0;
        for (var i = 1; i <= n; i++) {
            var count = 0;
            for (var j = 1; j <= n; j++) {
                if (dist[i][j] == 0) ++count;
            }
            if (count == 1) ++res;
        }
        return res;
    }
}