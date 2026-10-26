import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {
    private final int[] dr = new int[] {-1, 1, 0, 0};
    private final int[] dc = new int[] {0, 0, -1, 1};

    public int solution(String[] storage, String[] requests) {
        var m = storage.length;
        var n = storage[0].length();
        var grid = new int[m + 2][n + 2];

        for (var i = 0; i < m + 2; i++) {
            grid[i][0] = 1;
            grid[i][n + 1] = 1;
        }

        for (var i = 0; i < n + 2; i++) {
            grid[0][i] = 1;
            grid[m + 1][i] = 1;
        }

        for (var r : requests) {
            var nodes = new ArrayList<Node>();

            for (var i = 0; i < m; i++) {
                for (var j = 0; j < n; j++) {
                    if (storage[i].charAt(j) == r.charAt(0) && grid[i + 1][j + 1] == 0) {
                        if (r.length() == 1 && isConnect(grid, i + 1, j + 1)) {
                            nodes.add(new Node(i + 1, j + 1));
                        } else if (r.length() == 2) {
                            nodes.add(new Node(i + 1, j + 1));
                        }
                    }
                }
            }

            for (var node : nodes) {
                grid[node.r][node.c] = r.length();
            }

            var q = new ArrayDeque<Node>();
            for (var i = 0; i < grid.length; i++) {
                for (var j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 2 && isConnect(grid, i, j)) {
                        q.addLast(new Node(i, j));
                    }
                }
            }

            while (!q.isEmpty()) {
                var cur = q.removeFirst();
                grid[cur.r][cur.c] = 1;

                for (var dir = 0; dir < 4; dir++) {
                    var nr = cur.r + dr[dir];
                    var nc = cur.c + dc[dir];

                    if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == 2) {
                        q.addLast(new Node(nr, nc));
                    }
                }
            }
        }

        var res = 0;
        for (int[] arr : grid) {
            for (var j = 0; j < grid[0].length; j++) {
                if (arr[j] == 0) ++res;
            }
        }
        return res;
    }

    private boolean isConnect(int[][] grid, int r, int c) {
        for (var dir = 0; dir < 4; dir++) {
            var nr = r + dr[dir];
            var nc = c + dc[dir];

            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == 1) {
                return true;
            }
        }
        return false;
    }

    private record Node(int r, int c) {}
}