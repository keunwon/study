class Solution {
    private int[][] computers;
    private boolean[] visited;

    public int solution(int n, int[][] computers) {
        this.computers = computers;
        this.visited = new boolean[n];

        var res = 0;
        for (var i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                ++res;
            }
        }
        return res;
    }

    private void dfs(int cur) {
        for (var i = 0; i < visited.length; i++) {
            if (!visited[i] && computers[cur][i] == 1) {
                visited[i] = true;
                dfs(i);
            }
        }
    }
}