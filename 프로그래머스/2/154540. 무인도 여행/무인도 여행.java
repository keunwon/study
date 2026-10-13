import java.util.ArrayList;

class Solution {
    public int[] solution(String[] maps) {
        var visited = new boolean[maps.length][maps[0].length()];
        var res = new ArrayList<Integer>();

        for (var i = 0; i < maps.length; i++) {
            for (var j = 0; j < maps[i].length(); j++) {
                if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                    res.add(dfs(maps, visited, i, j));
                }
            }
        }
        return res.isEmpty()
                ? new int[] {-1}
                : res.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    private int dfs(String[] maps, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= maps.length || c < 0 || c >= maps[0].length()) {
            return 0;
        }
        if (visited[r][c] || maps[r].charAt(c) == 'X') {
            return 0;
        }

        visited[r][c] = true;
        var digit = maps[r].charAt(c) - '0';
        return digit
                + dfs(maps, visited, r - 1, c)
                + dfs(maps, visited, r + 1, c)
                + dfs(maps, visited, r, c + 1)
                + dfs(maps, visited, r, c - 1);
    }
}