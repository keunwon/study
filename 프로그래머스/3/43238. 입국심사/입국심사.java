import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        var l = 0L;
        var r = (long) times[times.length - 1] * n;

        while (l <= r) {
            var m = l + (r - l) / 2;
            var sum = 0L;

            for (var t : times) {
                sum += m / t;
            }

            if (sum >= n) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}