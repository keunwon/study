class Solution {
    public int[] solution(int n, int m) {
        var gcd = gcd(n, m);
        return new int[] {gcd, n * m / gcd};
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}