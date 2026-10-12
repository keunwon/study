class Solution {
    public int solution(int[] arr) {
        var lcm = 1;
        for (var num : arr) {
            var gcd = gcd(lcm, num);
            lcm = lcm * num / gcd;
        }
        return lcm;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}