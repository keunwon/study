class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        var d = 0;
        var p = 0;
        var res = 0L;

        for (var i = n - 1; i >= 0; i--) {
            d += deliveries[i];
            p += pickups[i];

            while (d > 0 || p > 0) {
                d -= cap;
                p -= cap;
                res += (i + 1L) * 2;
            }
        }
        return res;
    }
}