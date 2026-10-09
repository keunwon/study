import java.util.Arrays;
import java.util.Comparator;

class Solution {
    private int[] parent;

    public int solution(int n, int[][] costs) {
        this.parent = new int[n];

        Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));
        Arrays.setAll(parent, i -> i);

        var res = 0;
        for (var cost : costs) {
            var a = cost[0];
            var b = cost[1];
            var d = cost[2];

            if (union(a, b)) res += d;
        }
        return res;
    }

    private boolean union(int a, int b) {
        var findA = find(a);
        var findB = find(b);

        if (findA == findB) {
            return false;
        }

        if (findA <= findB) parent[findB] = findA;
        else parent[findA] = findB;
        return true;
    }

    private int find(int n) {
        if (parent[n] == n) return n;

        var findN = find(parent[n]);
        parent[n] = findN;
        return findN;
    }
}