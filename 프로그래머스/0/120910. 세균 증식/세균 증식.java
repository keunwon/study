class Solution {
    public int solution(int n, int t) {
        for (var i = 0; i < t; i++) {
            n *= 2;
        }
        return n;
    }
}