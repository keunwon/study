class Solution {
    public long solution(int n) {
        if (n == 1) return 1L;
        
        var one = 1L;
        var two = 2L;

        for (var i = 3; i <= n; i++) {
            var next = (one + two) % 1234567;
            one = two;
            two = next;
        }
        return two;
    }
}