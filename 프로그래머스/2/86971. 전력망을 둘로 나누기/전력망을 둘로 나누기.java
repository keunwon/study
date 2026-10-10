import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] wires) {
        var graph = new boolean[n + 1][n + 1];
        var visited = new boolean[n + 1];
        var res = n + 1;

        for (var w : wires) {
            var u = w[0];
            var v = w[1];
            graph[u][v] = true;
            graph[v][u] = true;
        }

        for (var w : wires) {
            var u = w[0];
            var v = w[1];

            graph[u][v] = false;
            graph[v][u] = false;

            var count = dfs(graph, visited, 1);
            res = Math.min(res, Math.abs((n - count) - count));
            Arrays.fill(visited, false);

            graph[u][v] = true;
            graph[v][u] = true;
        }
        return res;
    }

    private int dfs(boolean[][] graph, boolean[] visited, int cur) {
        var res = 0;
        for (var i = 1; i < graph.length; i++) {
            if (!visited[i] && graph[cur][i]) {
                visited[i] = true;
                res += dfs(graph, visited, i) + 1;
            }
        }
        return res;
    }
}