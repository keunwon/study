class Solution {
    public int solution(int a, int b) {
        var n1 = Integer.parseInt(String.valueOf(a) + b);
        var n2 = Integer.parseInt(String.valueOf(b) + a);
        return Math.max(n1, n2);
    }
}