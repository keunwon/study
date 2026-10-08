class Solution {
    public int solution(int a, int b) {
        var n1 = Integer.valueOf(String.valueOf(a) + b);
        var n2 = 2 * a * b;
        return Math.max(n1, n2);
    }
}