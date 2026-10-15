import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import static java.util.Comparator.comparing;

class Solution {
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, comparing(o -> o[1]));

        var graph = new HashMap<String, PriorityQueue<String>>();
        for (var t : tickets) {
            var u = t[0];
            var v = t[1];
            graph.computeIfAbsent(u, i -> new PriorityQueue<>()).offer(v);
        }

        var route = new ArrayList<String>();
        dfs(route, graph, "ICN");
        return route.reversed().toArray(String[]::new);
    }

    private void dfs(List<String> route, Map<String, PriorityQueue<String>> graph, String cur) {
        var q = graph.get(cur);
        while (q != null && !q.isEmpty()) {
            var adj = q.poll();
            dfs(route, graph, adj);
        }
        route.add(cur);
    }
}