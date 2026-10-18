import java.util.Arrays;

class Solution {
    public int[] solution(int N, int[] stages) {
        var count = new int[N + 2];
        for (var s : stages) ++count[s];

        var nodes = new Node[N];
        var ret = stages.length;

        for (var i = 1; i <= N; i++) {
            var x = count[i];
            if (x > 0) {
                nodes[i - 1] = new Node(i, (double) x / ret);
                ret -= x;
            } else {
                nodes[i - 1] = new Node(i, 0.0);
            }
        }
        Arrays.sort(nodes);

        var res = new int[N];
        for (var i = 0; i < N; i++) {
            res[i] = nodes[i].id;
        }
        return res;
    }

    private static class Node implements Comparable<Node> {
        int id;
        double rates;

        public Node(int id, double rates) {
            this.id = id;
            this.rates = rates;
        }

        @Override
        public int compareTo(Node o) {
            if (rates == o.rates) {
                return Integer.compare(id, o.id);
            }
            return Double.compare(o.rates, rates);
        }
    }
}