class Solution {
    public int solution(int n) {
        var res = 0;
        while (n > 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }
}