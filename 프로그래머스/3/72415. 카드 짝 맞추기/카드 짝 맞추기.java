import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private int[][] board;
    private Map<Integer, List<Node>> positionMap;

    public int solution(int[][] board, int r, int c) {
        this.board = board;
        this.positionMap = new HashMap<>();

        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[i].length; j++) {
                var x = board[i][j];
                if (x > 0) {
                    positionMap.computeIfAbsent(x, _ -> new ArrayList<>()).add(new Node(i, j, 0));
                }
            }
        }
        return permutation(new Node(r, c, 0), new int[positionMap.size()], new boolean[7], 0);
    }

    private int permutation(Node start, int[] pick, boolean[] visited, int depth) {
        if (depth == pick.length) {
            return minMoveCount(start, pick, 0);
        }

        var res = (int) 1e9;
        for (var i = 1; i < 7; i++) {
            if (!visited[i] && positionMap.containsKey(i)) {
                visited[i] = true;
                pick[depth] = i;
                res = Math.min(res, permutation(start, pick, visited, depth + 1));
                visited[i] = false;
            }
        }
        return res;
    }

    private int minMoveCount(Node start, int[] pick, int depth) {
        if (depth == pick.length) {
            return 0;
        }

        var id = pick[depth];
        var nodes = positionMap.get(id);
        var dist1 = bfs(start, nodes.get(0)) + bfs(nodes.get(0), nodes.get(1)) + 2;
        var dist2 = bfs(start, nodes.get(1)) + bfs(nodes.get(1), nodes.get(0)) + 2;

        for (var node : nodes) {
            board[node.r][node.c] = 0;
        }

        dist1 += minMoveCount(nodes.get(1), pick, depth + 1);
        dist2 += minMoveCount(nodes.get(0), pick, depth + 1);

        for (var node : nodes) {
            board[node.r][node.c] = id;
        }
        return Math.min(dist1, dist2);
    }

    private int bfs(Node src, Node dest) {
        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};
        var m = board.length;
        var n = board[0].length;

        var q = new ArrayDeque<Node>();
        var visited = new boolean[m][n];

        q.addLast(src);
        visited[src.r][src.c] = true;

        while (!q.isEmpty()) {
            var cur = q.removeFirst();

            if (cur.r == dest.r && cur.c == dest.c) {
                return cur.d;
            }

            for (var dir = 0; dir < 4; dir++) {
                var r = cur.r + dr[dir];
                var c = cur.c + dc[dir];

                if (!isRange(r, c)) {
                    continue;
                }

                if (!visited[r][c]) {
                    visited[r][c] = true;
                    q.addLast(new Node(r, c, cur.d + 1));
                }

                while (isRange(r + dr[dir], c + dc[dir]) && board[r][c] == 0) {
                    r += dr[dir];
                    c += dc[dir];
                }

                if (!visited[r][c]) {
                    visited[r][c] = true;
                    q.addLast(new Node(r, c, cur.d + 1));
                }
            }
        }
        return (int) 1e9;
    }

    private boolean isRange(int r, int c) {
        return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
    }

    private record Node(int r, int c, int d) {}
}
