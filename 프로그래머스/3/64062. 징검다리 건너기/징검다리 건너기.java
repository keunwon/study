class Solution {
    public int solution(int[] stones, int k) {
        var l = 1;
        var r = 200_000_000;

        while (l <= r) {
            var m = l + (r - l) / 2;

            if (check(stones, m, k)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }

    private boolean check(int[] stones, int mid, int k) {
        var step = 0;

        for (var stone : stones) {
            if (mid > stone) {
                ++step;
                if (step == k) return false;
            } else {
                step = 0;
            }
        }
        return true;
    }
}