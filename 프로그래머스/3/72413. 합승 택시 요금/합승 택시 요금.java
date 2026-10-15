import java.util.Arrays;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        var dist = new int[n + 1][n + 1];

        for (var i = 0; i <= n; i++) {
            Arrays.fill(dist[i], (int) 2e7);
            dist[i][i] = 0;
        }

        for (var fare : fares) {
            var c = fare[0];
            var d = fare[1];
            var f = fare[2];

            dist[c][d] = f;
            dist[d][c] = f;
        }

        for (var i = 1; i <= n; i++) {
            for (var j = 1; j <= n; j++) {
                for (var k = 1; k <= n; k++) {
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }

        var res = Integer.MAX_VALUE;
        for (var i = 1; i <= n; i++) {
            var d = dist[s][i] + dist[i][a] + dist[i][b];
            res = Math.min(res, d);
        }
        return res;
    }
}