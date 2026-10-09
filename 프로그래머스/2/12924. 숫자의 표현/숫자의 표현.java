class Solution {
    public int solution(int n) {
        var curSum = 0;
        var res = 0;
        var l = 1;
        
        for (var i = 1; i <= n; i++) {
            curSum += i;
            
            while (curSum > n) {
                curSum -= l;
                ++l;
            }
            if (curSum == n) ++res;
        }
        return res;
    }
}