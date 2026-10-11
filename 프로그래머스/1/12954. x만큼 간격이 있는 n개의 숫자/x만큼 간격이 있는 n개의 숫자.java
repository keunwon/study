class Solution {
    public long[] solution(int x, int n) {
        var res = new long[n];
        var num = (long) x;
        
        for (var i = 0; i < n; i++) {
            res[i] = num;
            num += x;
        }
        return res;
    }
}