import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        var graph = new ArrayList<List<Integer>>(n + 1);
        var visited = new boolean[n + 1];

        for (var i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (var light : lighthouse) {
            var a = light[0];
            var b = light[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(graph, visited, 1, -1);

        var res = 0;
        for (var i = 1; i <= n; i++) {
            if (visited[i]) ++res;
        }
        return res;
    }

    private void dfs(List<List<Integer>> graph, boolean[] visited, int cur, int prev) {
        for (var next : graph.get(cur)) {
            if (next == prev || visited[next]) continue;

            dfs(graph, visited, next, cur);
            if (!visited[next] && !visited[cur]) {
                visited[cur] = true;
            }
        }
    }
}