import java.util.ArrayDeque;

class Solution {
    public int solution(String[] maps) {
        Node start = null;
        Node end = null;
        Node lever = null;

        for (var i = 0; i < maps.length; i++) {
            for (var j = 0; j < maps[0].length(); j++) {
                switch (maps[i].charAt(j)) {
                    case 'S' -> start = new Node(i, j, 0);
                    case 'L' -> lever = new Node(i, j, 0);
                    case 'E' -> end = new Node(i, j, 0);
                }
            }
        }

        var dist1 = bfs(maps, start, lever);
        if (dist1 == -1) return -1;

        var dist2 = bfs(maps, lever, end);
        if (dist2 == -1) return -1;

        return dist1 + dist2;
    }

    private int bfs(String[] maps, Node from, Node to) {
        var n = maps.length;
        var m = maps[0].length();
        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};

        var q = new ArrayDeque<Node>();
        var visited = new boolean[n][m];

        q.addLast(from);
        visited[from.r][from.c] = true;

        while (!q.isEmpty()) {
            var cur = q.removeFirst();

            if (cur.r == to.r && cur.c == to.c) {
                return cur.d;
            }

            for (var dir = 0; dir < 4; dir++) {
                var r = cur.r + dr[dir];
                var c = cur.c + dc[dir];

                if (r >= 0 && r < n && c >= 0 && c < m && maps[r].charAt(c) != 'X' && !visited[r][c]) {
                    q.addLast(new Node(r, c, cur.d + 1));
                    visited[r][c] = true;
                }
            }
        }
        return -1;
    }

    private static class Node {
        int r;
        int c;
        int d;

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}