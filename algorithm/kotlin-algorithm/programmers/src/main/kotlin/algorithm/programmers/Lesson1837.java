package algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lesson1837 {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<Integer>[] graph = new List[n + 1];

        for (var i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>(n);
        }
        for (var edge : edge_list) {
            var a = edge[0];
            var b = edge[1];
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        var dp = new int[k][n + 1];
        var inf = 100001;

        for (var i = 0; i < k; i++) {
            Arrays.fill(dp[i], inf);
        }
        dp[0][gps_log[0]] = 0;

        for (var i = 0; i < k - 1; i++) {
            for (var j = 1; j <= n; j++) {
                if (dp[i][j] == inf) continue;

                for (var next : graph[j]) {
                    var d = dp[i][j];
                    if (gps_log[i + 1] != next) ++d;
                    dp[i + 1][next] = Math.min(dp[i + 1][next], d);
                }
            }
        }

        var result = dp[k - 1][gps_log[k - 1]];
        if (result == inf) return -1;
        return result;
    }
}
