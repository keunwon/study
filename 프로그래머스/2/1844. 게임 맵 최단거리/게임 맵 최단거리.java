import java.util.ArrayDeque;

class Solution {
    public int solution(int[][] maps) {
        if (maps[0][0] == 0) {
            return -1;
        }

        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};
        var n = maps.length;
        var m = maps[0].length;

        var q = new ArrayDeque<int[]>();
        q.addLast(new int[] {0, 0, 1});

        while (!q.isEmpty()) {
            var cur = q.removeFirst();

            if (cur[0] == n - 1 && cur[1] == m - 1) {
                return cur[2];
            }

            for (var dir = 0; dir < 4; dir++) {
                var r = cur[0] + dr[dir];
                var c = cur[1] + dc[dir];

                if (r >= 0 && r < n && c >= 0 && c < m && maps[r][c] == 1) {
                    maps[r][c] = 0;
                    q.addLast(new int[] {r, c, cur[2] + 1});
                }
            }
        }
        return -1;
    }
}