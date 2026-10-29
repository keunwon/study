class Solution {
    public int solution(int n) {
        if (n == 1) return 1;
        
        var mod = 1_000_000_007;
        var one = 1;
        var two = 2;
       
        for (var i = 3; i <= n; i++) {
            var next = (one + two) % mod;
            one = two;
            two = next;
        }
        return two;
    }
}