package algorithm.programmers;

import java.util.LinkedList;

public class Lesson1829 {
    private final int[][] moves = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] picture;
    private boolean[][] visited;

    public int[] solution(int m, int n, int[][] picture) {
        this.picture = picture;
        this.visited = new boolean[m][n];
        var answer = new int[2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    var count = bfs(new int[]{i, j}, picture[i][j]);
                    ++answer[0];
                    answer[1] = Math.max(answer[1], count);
                }
            }
        }
        return answer;
    }

    private int bfs(int[] start, int target) {
        var queue = new LinkedList<int[]>();
        var count = 0;

        visited[start[0]][start[1]] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            var cur = queue.poll();
            ++count;

            for (var move : moves) {
                var nr = cur[0] + move[0];
                var nc = cur[1] + move[1];

                if (nr < 0 || nc < 0 || nr >= visited.length || nc >= visited[0].length) continue;
                if (visited[nr][nc] || picture[nr][nc] != target) continue;

                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
        return count;
    }
}
