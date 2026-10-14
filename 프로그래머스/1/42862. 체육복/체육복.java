class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        var count = new int[n + 1];

        for (var l : lost) --count[l];
        for (var r : reserve) ++count[r];

        var res = 0;
        for (var i = 1; i <= n; i++) {
            if (count[i] >= 0) {
                ++res;
                continue;
            }
            
            if (count[i - 1] > 0) {
                ++count[i];
                ++res;
            } else if (i + 1 <= n && count[i + 1] > 0) {
                ++count[i];
                --count[i + 1];
                ++res;
            }
        }
        return res;
    }
}