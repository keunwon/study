import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public int solution(int[][] game_board, int[][] table) {
        var gameBlocks = extractBlocks(game_board, 0);
        var tableBlocks = extractBlocks(table, 1);
        var visited = new boolean[tableBlocks.size()];
        var res = 0;

        for (var gameBlock : gameBlocks) {
            for (var i = 0; i < tableBlocks.size(); i++) {
                if (!visited[i] && match(gameBlock, tableBlocks.get(i))) {
                    visited[i] = true;
                    res += gameBlock.size();
                    break;
                }
            }
        }
        return res;
    }

    private boolean match(List<Node> gameBlock, List<Node> tableBlock) {
        if (gameBlock.size() != tableBlock.size()) {
            return false;
        }

        var curBlock = gameBlock.toArray(Node[]::new);
        for (var i = 0; i < 4; i++) {
            var valid = true;
            for (var j = 0; j < curBlock.length; j++) {
                if (!curBlock[j].equals(tableBlock.get(j))) {
                    valid = false;
                    break;
                }
            }

            if (valid) return true;

            rotated(curBlock);
        }
        return false;
    }

    private void rotated(Node[] block) {
        for (var i = 0; i < block.length; i++) {
            var b = block[i];
            block[i] = new Node(b.c, -b.r);
        }

        Arrays.sort(block);

        var first = block[0];
        for (var i = 0; i < block.length; i++) {
            var b = block[i];
            block[i] = new Node(b.r - first.r, b.c - first.c);
        }
    }

    private List<List<Node>> extractBlocks(int[][] board, int target) {
        var m = board.length;
        var n = board[0].length;
        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};

        var visited = new boolean[m][n];
        var blocks = new ArrayList<List<Node>>();

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                if (board[i][j] != target || visited[i][j]) {
                    continue;
                }

                var q = new ArrayDeque<Node>();
                var block = new ArrayList<Node>();

                q.addLast(new Node(i, j));
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    var cur = q.removeFirst();
                    block.add(new Node(cur.r - i, cur.c - j));

                    for (var dir = 0; dir < 4; dir++) {
                        var r = cur.r + dr[dir];
                        var c = cur.c + dc[dir];

                        if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == target && !visited[r][c]) {
                            visited[r][c] = true;
                            q.addLast(new Node(r, c));
                        }
                    }
                }

                Collections.sort(block);
                blocks.add(block);
            }
        }
        return blocks;
    }

    private record Node(int r, int c) implements Comparable<Node> {

        @Override
        public int compareTo(Node o) {
            if (r != o.r) return Integer.compare(r, o.r);
            return Integer.compare(c, o.c);
        }
    }
}