class Solution {
    public int solution(int n) {
        var sum = 0;
        for (var i = 2; i <= n; i += 2) sum += i;
        return sum;
    }
}