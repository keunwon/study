import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(int N, int[][] road, int K) {
        var graph = new ArrayList<List<Node>>(N + 1);

        for (var i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (var r : road) {
            var u = r[0];
            var v = r[1];
            var d = r[2];
            graph.get(u).add(new Node(v, d));
            graph.get(v).add(new Node(u, d));
        }

        var q = new ArrayDeque<Node>();
        var dist = new int[N + 1];

        q.addLast(new Node(1, 0));
        Arrays.fill(dist, (int) 1e9);
        dist[1] = 0;

        while (!q.isEmpty()) {
            var cur = q.removeFirst();

            for (var adj : graph.get(cur.id)) {
                var nextTime = cur.time + adj.time;
                if (dist[adj.id] > nextTime) {
                    dist[adj.id] = nextTime;
                    q.addLast(new Node(adj.id, nextTime));
                }
            }
        }

        var res = 0;
        for (var i = 1; i <= N; i++) {
            if (K >= dist[i]) ++res;
        }
        return res;
    }

    private static class Node {
        int id;
        int time;

        public Node(int id, int d) {
            this.id = id;
            this.time = d;
        }
    }
}