import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public int solution(int[][] land) {
        var m = land.length;
        var n = land[0].length;
        var dr = new int[] {-1, 1, 0, 0};
        var dc = new int[] {0, 0, -1, 1};

        var groupLand = new int[m][n];
        var groupCountMap = new HashMap<Integer, Integer>();
        var groupId = 0;

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                if (land[i][j] == 0) {
                    continue;
                }

                var q = new ArrayDeque<int[]>();
                var count = 0;

                q.addLast(new int[] {i, j});
                land[i][j] = 0;
                ++groupId;

                while (!q.isEmpty()) {
                    var cur = q.removeFirst();

                    groupLand[cur[0]][cur[1]] = groupId;
                    ++count;

                    for (var dir = 0; dir < 4; dir++) {
                        var r = cur[0] + dr[dir];
                        var c = cur[1] + dc[dir];

                        if (r >= 0 && r < m && c >= 0 && c < n && land[r][c] == 1) {
                            land[r][c] = 0;
                            groupLand[r][c] = groupId;
                            q.addLast(new int[] {r, c});
                        }
                    }
                }
                groupCountMap.put(groupId, count);
            }
        }

        var visitedGroup = new boolean[groupId + 1];
        var maxOil = 0;

        for (var j = 0; j < n; j++) {
            var oil = 0;
            for (var i = 0; i < m; i++) {
                var id = groupLand[i][j];
                if (id > 0 && !visitedGroup[id]) {
                    visitedGroup[id] = true;
                    oil += groupCountMap.getOrDefault(id, 0);
                }
            }
            
            maxOil = Math.max(maxOil, oil);
            Arrays.fill(visitedGroup, false);
        }
        return maxOil;
    }
}