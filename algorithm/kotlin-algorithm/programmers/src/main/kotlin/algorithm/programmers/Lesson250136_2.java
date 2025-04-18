package algorithm.programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Lesson250136_2 {
    private final int[][] moves = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int solution(int[][] land) {
        var groupLand = new int[land.length][land[0].length];
        var visited = new boolean[land.length][land[0].length];
        var map = new HashMap<Integer, Integer>();
        var id = 1;

        for (var i = 0; i < land.length; i++) {
            for (var j = 0; j < land[i].length; j++) {
                if (land[i][j] == 0 || visited[i][j]) continue;

                var q = new LinkedList<int[]>();
                var area = 0;

                visited[i][j] = true;
                q.offer(new int[]{i, j});

                while (!q.isEmpty()) {
                    var cur = q.poll();

                    ++area;
                    groupLand[cur[0]][cur[1]] = id;

                    for (var move : moves) {
                        var nr = cur[0] + move[0];
                        var nc = cur[1] + move[1];

                        if (nr < 0 || nc < 0 || nr >= land.length || nc >= land[0].length) continue;
                        if (land[nr][nc] == 0 || visited[nr][nc]) continue;

                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }

                map.put(id++, area);
            }
        }

        var result = 0;
        for (var i = 0; i < groupLand[0].length; i++) {
            var set = new HashSet<Integer>();

            for (var j = 0; j < groupLand.length; j++) {
                if (groupLand[j][i] != 0) set.add(groupLand[j][i]);
            }

            var sum = set.stream().mapToInt(map::get).sum();
            result = Math.max(result, sum);
        }
        return result;
    }
}
