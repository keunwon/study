import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public long solution(int[] a, int[][] edges) {
        var graph = new ArrayList<List<Integer>>();
        var indegree = new int[a.length];
        var weight = new long[a.length];

        for (var i = 0; i < a.length; i++) {
            graph.add(new ArrayList<>());
        }

        var total = 0L;
        for (var i = 0; i < a.length; i++) {
            var x = a[i];
            total += x;
            weight[i] = x;
        }

        if (total != 0L) {
            return -1L;
        }

        for (var edge : edges) {
            var u = edge[0];
            var v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
            ++indegree[u];
            ++indegree[v];
        }

        var q = new ArrayDeque<Integer>();
        var res = 0L;

        for (var i = 0; i < indegree.length; i++) {
            if (indegree[i] == 1) q.addLast(i);
        }

        while (!q.isEmpty()) {
            var cur = q.removeFirst();
            var w = weight[cur];

            indegree[cur] = 0;
            weight[cur] = 0;
            res += Math.abs(w);

            for (var adj : graph.get(cur)) {
                if (indegree[adj] == 0) continue;

                --indegree[adj];
                weight[adj] += w;
                if (indegree[adj] == 1) q.addLast(adj);
            }
        }
        return res;
    }
}