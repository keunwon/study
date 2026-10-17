import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        if (s / n == 0) {
            return new int[] {-1};
        }

        if (n == 1) {
            return new int[] {s};
        }

        var div = s / n;
        var mod = s % n;
        var res = new int[n];

        Arrays.fill(res, div);

        for (var i = n - 1; i >= 0; i--) {
            if (mod == 0) break;
            ++res[i];
            --mod;
        }
        return res;
    }
}