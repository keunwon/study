class Solution {
    public int solution(int n) {
        return ((n * 6) / gcd(n, 6)) / 6;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            var mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }
}