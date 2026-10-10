class Solution {
    public int[] solution(int[] prices) {
        var n = prices.length;
        var res = new int[n];
        var stk = new int[n];
        var top = -1;
        
        for (var i = 0; i < n; i++) {
            while (top > -1 && prices[stk[top]] > prices[i]) {
                var j = stk[top--];
                res[j] = i - j;
            }
            stk[++top] = i;
        }
        
        while (top > -1) {
            var i = stk[top--];
            res[i] = n - i - 1;
        }
        return res;
    }
}