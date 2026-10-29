class Solution {
    private int[] weak;
    private int[] dist;
    private int[] spreadWeak;

    public int solution(int n, int[] weak, int[] dist) {
        this.weak = weak;
        this.dist = dist;
        this.spreadWeak = new int[weak.length * 2];

        for (var i = 0; i < weak.length; i++) {
            spreadWeak[i] = weak[i];
            spreadWeak[i + weak.length] = weak[i] + n;
        }

        for (var size = 1; size <= dist.length; size++) {
            var valid = dfs(new int[size], new boolean[dist.length], 0);
            if (valid) return size;
        }
        return -1;
    }

    private boolean dfs(int[] pick, boolean[] visited, int depth) {
        if (depth == pick.length) {
            lo:
            for (var i = 0; i < weak.length; i++) {
                var sIdx = i;
                var pIdx = 0;

                for (var j = i; j < i + weak.length; j++) {
                    var diff = spreadWeak[j] - spreadWeak[sIdx];
                    if (diff > pick[pIdx]) {
                        sIdx = j;
                        if (++pIdx == pick.length) continue lo;
                    }
                }
                return true;
            }
            return false;
        }

        for (var i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                pick[depth] = dist[i];

                if (dfs(pick, visited, depth + 1)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}