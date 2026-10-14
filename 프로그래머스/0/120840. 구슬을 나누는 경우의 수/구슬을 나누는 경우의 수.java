import java.math.BigInteger;

class Solution {
    public int solution(int balls, int share) {
        var x = factory(balls - share).multiply(factory(share));
        return factory(balls).divide(x).intValue();
    }

    private BigInteger factory(int n) {
        if (n <= 1) return BigInteger.ONE;
        return BigInteger.valueOf(n).multiply(factory(n - 1));
    }
}