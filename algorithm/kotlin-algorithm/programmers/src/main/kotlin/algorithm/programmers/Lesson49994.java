package algorithm.programmers;

import java.util.Map;

public class Lesson49994 {
    private final int[][] moves = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private final Map<Character, Integer> moveMap = Map.of('U', 0, 'R', 1, 'D', 2, 'L', 3);

    public int solution(String dirs) {
        var visited = new boolean[11][11][4];
        var r = 5;
        var c = 5;
        var answer = 0;

        for (char dir : dirs.toCharArray()) {
            var mIndex = moveMap.get(dir);
            var nr = r + moves[mIndex][0];
            var nc = c + moves[mIndex][1];

            if (nr < 0 || nc < 0 || nr >= visited.length || nc >= visited[0].length) continue;

            if (!visited[nr][nc][mIndex]) {
                visited[nr][nc][mIndex] = true;
                visited[r][c][(mIndex + 2) % moveMap.size()] = true;
                ++answer;
            }
            r = nr;
            c = nc;
        }
        return answer;
    }
}
