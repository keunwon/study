import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[] info;
    private List<List<Integer>> graph;
    private int maxSheep = 0;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.graph = new ArrayList<>(info.length);

        for (var i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (var edge : edges) {
            var u = edge[0];
            var v = edge[1];
            graph.get(u).add(v);
        }

        dfs(0, 0, 0, new ArrayList<>());
        return maxSheep;
    }

    private void dfs(int wolf, int sheep, int cur, List<Integer> nodes) {
        if (info[cur] == 0) ++sheep;
        else ++wolf;

        if (wolf >= sheep) return;

        maxSheep = Math.max(maxSheep, sheep);

        var children = new ArrayList<>(graph.get(cur));
        for (var node : nodes) {
            if (node != cur) children.add(node);
        }

        for (var child : children) {
            dfs(wolf, sheep, child, children);
        }
    }
}