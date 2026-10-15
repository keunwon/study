import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toSet;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        var graph = new ArrayList<List<Node>>(n + 1);

        for (var i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (var path : paths) {
            var i = path[0];
            var j = path[1];
            var w = path[2];

            graph.get(i).add(new Node(j, w));
            graph.get(j).add(new Node(i, w));
        }

        var q = new PriorityQueue<Node>(comparingInt(o -> o.w));
        var dist = new int[n + 1];
        var summitSet = Arrays.stream(summits).boxed().collect(toSet());

        Arrays.fill(dist, (int) 1e9);
        for (var gate : gates) {
            q.offer(new Node(gate, 0));
            dist[gate] = 0;
        }

        while (!q.isEmpty()) {
            var cur = q.poll();

            if (summitSet.contains(cur.id)) continue;
            if (cur.w > dist[cur.id]) continue;

            for (var adj : graph.get(cur.id)) {
                var maxD = Math.max(cur.w, adj.w);
                if (dist[adj.id] > maxD) {
                    dist[adj.id] = maxD;
                    q.offer(new Node(adj.id, maxD));
                }
            }
        }

        
        var s = -1;
        var min = Integer.MAX_VALUE;

        for (var summit : summits) {
            if (min > dist[summit]) {
                s = summit;
                min = dist[summit];
            } else if (min == dist[summit] && s > summit) {
                s = summit;
            }
        }
        return new int[] {s, min};
    }

    private static class Node {
        int id;
        int w;

        public Node(int id, int w) {
            this.id = id;
            this.w = w;
        }
    }
}