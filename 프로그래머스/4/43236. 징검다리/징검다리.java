import java.util.Arrays;

public class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        var l = 1;
        var r = distance;

        while (l <= r) {
            var m = l + (r - l) / 2;

            if (check(distance, rocks, n, m)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }

    private boolean check(int distance, int[] rocks, int n, int minNum) {
        var pre = 0;
        for (var r : rocks) {
            var diff = r - pre;
            if (minNum > diff) {
                if (n == 0) return false;
                --n;
            } else {
                pre = r;
            }
        }
        if (minNum > distance - pre) --n;
        return n >= 0;
    }
}
