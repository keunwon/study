class Solution {
    public int solution(int a, int b) {
        var gcd = gcd(a, b);
        b /= gcd;

        while (b != 1) {
            if (b % 2 == 0) {
                b /= 2;
            } else if (b % 5 == 0) {
                b /= 5;
            } else {
                return 2;
            }
        }
        return 1;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}