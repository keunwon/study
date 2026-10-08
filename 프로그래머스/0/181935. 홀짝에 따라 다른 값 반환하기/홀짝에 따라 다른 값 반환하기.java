class Solution {
    public int solution(int n) {
        if (n % 2 == 1) {
            var res = 0;
            for (var i = 1; i <= n; i += 2) {
                res += i;
            }
            return res;
        }
        
        var res = 0;
        for (var i = 2; i <= n; i += 2) {
            res += i * i;
        }
        return res;
    }
}