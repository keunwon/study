import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;

class Solution {
    public int solution(int[][] land, int height) {
        var m = land.length;
        var n = land[0].length;
        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};

        var groupLand = new int[m][n];
        var groupId = 0;

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                if (groupLand[i][j] != 0) {
                    continue;
                }

                var q = new ArrayDeque<int[]>();
                groupLand[i][j] = ++groupId;
                q.addLast(new int[] {i, j});

                while (!q.isEmpty()) {
                    var cur = q.removeFirst();
                    var x = land[cur[0]][cur[1]];

                    for (var dir = 0; dir < 4; dir++) {
                        var r = cur[0] + dr[dir];
                        var c = cur[1] + dc[dir];

                        if (r >= 0
                                && r < m
                                && c >= 0
                                && c < n
                                && groupLand[r][c] == 0
                                && height >= Math.abs(x - land[r][c])) {
                            groupLand[r][c] = groupId;
                            q.addLast(new int[] {r, c});
                        }
                    }
                }
            }
        }

        var nodes = new ArrayList<Node>();
        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                for (var dir = 0; dir < 4; dir++) {
                    var r = i + dr[dir];
                    var c = j + dc[dir];

                    if (r >= 0 && r < m && c >= 0 && c < n && groupLand[r][c] != groupLand[i][j]) {
                        var diff = Math.abs(land[r][c] - land[i][j]);
                        nodes.add(new Node(groupLand[r][c], groupLand[i][j], diff));
                    }
                }
            }
        }
        nodes.sort(Comparator.comparingInt(o -> o.diff));

        var parent = new int[groupId + 1];
        for (var i = 0; i <= groupId; i++) {
            parent[i] = i;
        }

        var res = 0;
        for (var node : nodes) {
            if (union(parent, node.teamA, node.teamB)) {
                res += node.diff;
            }
        }
        return res;
    }

    private boolean union(int[] parent, int a, int b) {
        var findA = find(parent, a);
        var findB = find(parent, b);

        if (findA == findB) {
            return false;
        }

        if (findA <= findB) parent[findB] = findA;
        else parent[findA] = findB;

        return true;
    }

    private int find(int[] parent, int x) {
        if (parent[x] == x) return x;

        var findX = find(parent, parent[x]);
        parent[x] = findX;
        return findX;
    }

    private static class Node {
        int teamA;
        int teamB;
        int diff;

        public Node(int teamA, int teamB, int diff) {
            this.teamA = teamA;
            this.teamB = teamB;
            this.diff = diff;
        }
    }
}