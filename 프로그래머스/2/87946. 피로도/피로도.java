class Solution {
    private int[][] dungeons;
    private boolean[] visited;
    private int res = 0;

    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        this.visited = new boolean[dungeons.length];

        dfs(k, 0);
        return res;
    }

    private void dfs(int k, int depth) {
        res = Math.max(res, depth);

        for (var i = 0; i < dungeons.length; i++) {
            var du = dungeons[i];
            if (!visited[i] && k >= du[0]) {
                visited[i] = true;
                dfs(k - du[1], depth + 1);
                visited[i] = false;
            }
        }
    }
}