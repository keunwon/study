class Solution {
    public int solution(int k, int m, int[] score) {
        var count = new int[k + 1];
        for (var s : score) ++count[s];

        var res = 0;
        for (var i = k; i >= 1; i--) {
            var n = count[i] / m;
            res += i * m * n;
            count[i - 1] += count[i] % m;
        }
        return res;
    }
}