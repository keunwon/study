class Solution {
    public int solution(int n) {
        var n1 = 0;
        var n2 = 1;
        
        for (var i = 2; i <= n; i++) {
            var next = (n1 + n2) % 1234567;
            n1 = n2;
            n2 = next;
        }
        return n2;
    }
}