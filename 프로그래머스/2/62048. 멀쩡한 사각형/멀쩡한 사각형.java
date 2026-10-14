class Solution {
    public long solution(int w, int h) {
        var gcd = gcd(w, h);
        return ((long) w * h) - (long) ((w / gcd) + (h / gcd) - 1) * gcd;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            var x = a % b;
            a = b;
            b = x;
        }
        return a;
    }
}