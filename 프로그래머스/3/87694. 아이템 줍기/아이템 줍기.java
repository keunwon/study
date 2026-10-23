import java.util.ArrayDeque;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        var grid = new int[102][102];

        for (var re : rectangle) {
            for (var i = re[0] * 2; i <= re[2] * 2; i++) {
                for (var j = re[1] * 2; j <= re[3] * 2; j++) {
                    grid[i][j] = 1;
                }
            }
        }

        for (var re : rectangle) {
            for (var i = re[0] * 2 + 1; i < re[2] * 2; i++) {
                for (var j = re[1] * 2 + 1; j < re[3] * 2; j++) {
                    grid[i][j] = 0;
                }
            }
        }

        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};
        var destX = itemX * 2;
        var destY = itemY * 2;

        var q = new ArrayDeque<Node>();

        q.addLast(new Node(characterX * 2, characterY * 2, 0));
        grid[characterX * 2][characterY * 2] = 0;

        while (!q.isEmpty()) {
            var cur = q.removeFirst();

            if (cur.r == destX && cur.c == destY) {
                return cur.d / 2;
            }

            for (var dir = 0; dir < 4; dir++) {
                var r = cur.r + dr[dir];
                var c = cur.c + dc[dir];

                if (r >= 0 && r < 102 && c >= 0 && c < 102 && grid[r][c] == 1) {
                    grid[r][c] = 0;
                    q.addLast(new Node(r, c, cur.d + 1));
                }
            }
        }
        return 0;
    }

    private record Node(int r, int c, int d) {}
}