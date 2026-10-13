class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        var gcdA = arrayA[0];
        var gcdB = arrayB[0];

        for (var i = 0; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }

        var max = 0;
        var valid = true;

        for (var b : arrayB) {
            if (b % gcdA == 0) {
                valid = false;
                break;
            }
        }
        if (valid) max = gcdA;

        valid = true;
        for (var a : arrayA) {
            if (a % gcdB == 0) {
                valid = false;
                break;
            }
        }
        if (valid) max = Math.max(max, gcdB);

        return max;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}