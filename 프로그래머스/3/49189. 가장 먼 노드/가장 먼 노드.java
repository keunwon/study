import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(int n, int[][] edge) {
        var graph = new ArrayList<List<Integer>>();

        for (var i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (var e : edge) {
            var u = e[0];
            var v = e[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        var q = new ArrayDeque<Integer>();
        var dist = new int[n + 1];

        q.addLast(1);
        Arrays.fill(dist, (int) 1e9);
        dist[0] = 0;
        dist[1] = 0;

        while (!q.isEmpty()) {
            var cur = q.removeFirst();

            for (var adj : graph.get(cur)) {
                var nextD = dist[cur] + 1;
                if (dist[adj] > nextD) {
                    dist[adj] = nextD;
                    q.addLast(adj);
                }
            }
        }

        var max = -1;
        for (var i = 2; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }
        
        var count = 0;
        for (var i = 2; i <= n; i++) {
            if (dist[i] == max) ++count;
        }
        return count;
    }
}