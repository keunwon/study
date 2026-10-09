class Solution {
    public int solution(int n) {
        var res = 0;
        for (var i = 1; i <= n; i++) {
            if (n % i == 0) res += i;
        }
        return res;
    }
}