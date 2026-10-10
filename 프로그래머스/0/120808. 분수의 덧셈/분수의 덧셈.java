class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        var n = (numer1 * denom2) + (numer2 * denom1);
        var d = denom1 * denom2;
        
        var gcd = gcd(d, n);
        return new int[] {n / gcd, d / gcd};
    }
    
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}