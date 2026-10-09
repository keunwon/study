class Solution {
    public boolean solution(int x) {
        var sum = 0;
        var cur = x;
        
        while (cur > 0) {
            sum += (cur % 10);
            cur /= 10;
        }
        return x % sum == 0;
    }
}