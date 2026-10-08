class Solution {
    public int solution(int n) {
        var sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n ? 1 : 2;
    }
}