class Solution {
    public int solution(int n) {
        var count = new int[n + 1];
        for (var i = 1; i <= n; i++) {
            for (var j = i; j <= n; j += i) {
                ++count[j];
            }
        }

        var res = 0;
        for (var i = 1; i <= n; i++) {
            if (count[i] >= 3) ++res;
        }
        return res;
    }
}