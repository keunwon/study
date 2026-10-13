class Solution {
    private int[] res = new int[11];
    private int maxDiff = 0;

    public int[] solution(int n, int[] info) {
        dfs(info, new int[11], 0, n);
        return maxDiff == 0 ? new int[] {-1} : res;
    }

    private void dfs(int[] apeach, int[] ryan, int startIdx, int n) {
        if (n == 0) {
            var apeachPoint = 0;
            var ryanPoint = 0;

            for (var i = 0; i < 11; i++) {
                if (apeach[i] == 0 && ryan[i] == 0) continue;

                var point = 10 - i;
                if (apeach[i] >= ryan[i]) {
                    apeachPoint += point;
                } else {
                    ryanPoint += point;
                }
            }

            var diff = ryanPoint - apeachPoint;
            if (diff > maxDiff) {
                maxDiff = diff;
                System.arraycopy(ryan, 0, res, 0, 11);
            } else if (diff == maxDiff) {
                for (var i = 10; i >= 0; i--) {
                    if (ryan[i] > res[i]) {
                        System.arraycopy(ryan, 0, res, 0, 11);
                        break;
                    } else if (ryan[i] < res[i]) {
                        break;
                    }
                }
            }
            return;
        }

        for (var i = startIdx; i < 11; i++) {
            ++ryan[i];
            dfs(apeach, ryan, i, n - 1);
            --ryan[i];
        }
    }
}