import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        var graph = new ArrayList<List<Integer>>(n + 1);
        for (var i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (var r : roads) {
            var a = r[0];
            var b = r[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        var q = new ArrayDeque<int[]>();
        var dist = new int[n + 1];

        q.addLast(new int[] {destination, 0});
        Arrays.fill(dist, (int) 1e9);
        dist[destination] = 0;

        while (!q.isEmpty()) {
            var cur = q.removeFirst();

            for (var adj : graph.get(cur[0])) {
                var nextD = cur[1] + 1;
                if (dist[adj] > nextD) {
                    dist[adj] = nextD;
                    q.addLast(new int[] {adj, nextD});
                }
            }
        }

        var res = new int[sources.length];
        for (var i = 0; i < sources.length; i++) {
            var s = sources[i];
            res[i] = dist[s] == (int) 1e9 ? -1 : dist[s];
        }
        return res;
    }
}