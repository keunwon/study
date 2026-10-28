import java.util.HashMap;

class Solution {
    public int[] solution(int[][] edges) {
        var indegree = new HashMap<Integer, Integer>(edges.length);
        var outdegree = new HashMap<Integer, Integer>(edges.length);
        var res = new int[4];

        for (var edge : edges) {
            var a = edge[0];
            var b = edge[1];
            outdegree.put(a, outdegree.getOrDefault(a, 0) + 1);
            indegree.put(b, indegree.getOrDefault(b, 0) + 1);
        }

        for (var e : outdegree.entrySet()) {
            if (e.getValue() > 1) {
                if (!indegree.containsKey(e.getKey())) {
                    res[0] = e.getKey();
                } else {
                    ++res[3];
                }
            }
        }

        for (var e : indegree.entrySet()) {
            if (!outdegree.containsKey(e.getKey())) {
                ++res[2];
            }
        }

        res[1] = outdegree.get(res[0]) - res[2] - res[3];
        return res;
    }
}